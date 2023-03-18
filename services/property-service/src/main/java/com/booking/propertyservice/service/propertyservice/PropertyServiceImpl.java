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
import com.booking.propertyservice.service.propertyservice.helper.PropertyServiceHelper;
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
    private final PropertyServiceHelper propertyServiceHelper;
    private final OutboxHelper outboxHelper;
    private final ModelMapper modelMapper;

    private static final int PAGE_SIZE = 5;

    @Override
    public PropertyPageDto searchProperties(SearchCriteria searchCriteria) {
        log.info("Search Properties");

        List<String> reservedPropertiesIds = reservationRepository.findReservedPropertiesIds(searchCriteria.getCountry(),
                searchCriteria.getCheckIn(), searchCriteria.getCheckOut());

        Pageable pageable = PageRequest.of(searchCriteria.getCurrentPage(), PAGE_SIZE);

        PropertyPage propertyPage = propertyRepository.findUnreservedProperties(reservedPropertiesIds,
                searchCriteria.getGuestNumber(), searchCriteria.getCountry(), pageable);

        return PropertyMapper.toPropertyPageDto(propertyPage);
    }

    @Override
    public PropertyDetailsDto getPropertyById(String propertyId) {
        log.info("Get property with id: {}", propertyId);

        Property property = propertyRepository.findPropertyByPropertyId(propertyId);

        return modelMapper.map(property, PropertyDetailsDto.class);
    }

    @Override
    @Transactional
    public PropertyDetailsDto createProperty(MultipartFile[] images, String propertyJson) {
        log.info("Create property");

        PropertyDetailsDto propertyDetailsDto = propertyServiceHelper.extractPropertyDetailsDtoFromJson(propertyJson);

        propertyServiceHelper.saveImages(images, propertyDetailsDto);

        propertyServiceHelper.saveOwner(propertyDetailsDto.getOwner());

        return propertyServiceHelper.saveProperty(propertyDetailsDto);
    }

    @Override
    @Transactional
    public void deleteProperty(String propertyId) {
        log.info("Delete property with id: {}", propertyId);

        Property property = propertyRepository.findPropertyByPropertyId(propertyId);

        propertyRepository.deletePropertyByPropertyId(propertyId);

        outboxHelper.saveOutboxMessage(property);
    }

    @Override
    public List<PropertyDto> getPropertiesByOwnerId(String ownerId) {
        log.info("Get user's properties");
        List<Property> propertyList = propertyRepository.findPropertiesByOwnerId(ownerId);

        return propertyList.stream()
                .map(propertyServiceHelper::getPropertyDto)
                .toList();
    }
}
