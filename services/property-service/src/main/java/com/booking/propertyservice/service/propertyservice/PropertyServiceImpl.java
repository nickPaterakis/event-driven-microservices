package com.booking.propertyservice.service.propertyservice;

import com.booking.propertyservice.dto.mapper.PropertyMapper;
import com.booking.propertyservice.dto.request.SearchCriteria;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyPageDto;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.model.PropertyPage;
import com.booking.propertyservice.outbox.helper.OutboxHelper;
import com.booking.propertyservice.repository.propertyrepository.PropertyRepository;
import com.booking.propertyservice.repository.reservationrepository.ReservationRepository;
import com.booking.propertyservice.service.imageservice.ImageService;
import com.booking.propertyservice.service.ownerservice.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final ReservationRepository reservationRepository;
    private final OwnerService ownerService;
    private final OutboxHelper outboxHelper;
    private final ModelMapper modelMapper;
    private final ImageService imageService;

    private static final int PAGE_SIZE = 5;

    @Override
    public PropertyPageDto searchProperties(SearchCriteria searchCriteria) {
        log.info("Searching properties with criteria: country={}, checkIn={}, checkOut={}, guestNumber={}",
                searchCriteria.getCountry(), searchCriteria.getCheckIn(), searchCriteria.getCheckOut(),
                searchCriteria.getGuestNumber());

        List<String> reservedPropertiesIds = reservationRepository.findReservedPropertiesIds(
                searchCriteria.getCountry(),
                searchCriteria.getCheckIn(), searchCriteria.getCheckOut());

        Pageable pageable = PageRequest.of(searchCriteria.getCurrentPage(), PAGE_SIZE);

        PropertyPage propertyPage = propertyRepository.findUnreservedProperties(reservedPropertiesIds,
                searchCriteria.getGuestNumber(), searchCriteria.getCountry(), pageable);

        log.info("Found {} properties matching criteria", propertyPage.getTotalElements());
        return PropertyMapper.toPropertyPageDto(propertyPage);
    }

    @Override
    public PropertyDetailsDto getPropertyById(String propertyId) {
        log.info("Fetching property details for propertyId: {}", propertyId);

        Property property = propertyRepository.findPropertyByPropertyId(propertyId);

        log.info("Successfully fetched property details for propertyId: {}", propertyId);
        return modelMapper.map(property, PropertyDetailsDto.class);
    }

    @Override
    @Transactional
    public PropertyDetailsDto createProperty(MultipartFile[] images, PropertyDetailsDto propertyDetailsDto) {
        log.info("Initiating property creation for owner: {}", propertyDetailsDto.getOwner().getEmail());

        imageService.saveImages(images, propertyDetailsDto);

        ownerService.saveOwner(propertyDetailsDto.getOwner());

        Property property = modelMapper.map(propertyDetailsDto, Property.class);
        Property savedProperty = propertyRepository.saveProperty(property);

        log.info("Successfully created property with id: {}", savedProperty.getId());
        return modelMapper.map(savedProperty, PropertyDetailsDto.class);
    }

    @Override
    @Transactional
    public void deleteProperty(String propertyId) {
        log.info("Request to delete property with id: {}", propertyId);

        Property property = propertyRepository.findPropertyByPropertyId(propertyId);

        propertyRepository.deletePropertyByPropertyId(propertyId);

        outboxHelper.saveOutboxMessage(property);
        log.info("Successfully deleted property with id: {}", propertyId);
    }

    @Override
    public List<PropertyDto> getPropertiesByOwnerId(String ownerId) {
        log.info("Fetching properties for ownerId: {}", ownerId);
        List<Property> propertyList = propertyRepository.findPropertiesByOwnerId(ownerId);

        log.info("Found {} properties for ownerId: {}", propertyList.size(), ownerId);
        return propertyList.stream()
                .map(PropertyMapper::toPropertyDto)
                .toList();
    }
}
