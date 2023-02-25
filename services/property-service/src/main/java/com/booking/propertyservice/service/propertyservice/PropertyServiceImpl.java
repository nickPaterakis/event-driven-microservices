package com.booking.propertyservice.service.propertyservice;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.dto.mapper.PropertyMapper;
import com.booking.propertyservice.dto.request.SearchCriteria;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyDtoPage;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.outbox.helper.OutboxHelper;
import com.booking.propertyservice.repository.propertyrepository.PropertyRepository;
import com.booking.propertyservice.repository.reservationrepository.ReservationRepository;
import com.booking.propertyservice.service.propertyservice.helper.PropertyServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public PropertyDtoPage searchProperties(SearchCriteria searchCriteria) {
        log.info("Search Properties");

        List<String> reservedProperties = reservationRepository.findReservedProperties(searchCriteria.getCountry(),
                searchCriteria.getCheckIn(), searchCriteria.getCheckOut());

        Pageable pageable = PageRequest.of(searchCriteria.getCurrentPage(), PAGE_SIZE);

        Page<Property> propertyPage = propertyRepository.findUnreservedProperties(reservedProperties,
                searchCriteria.getGuestNumber(), searchCriteria.getCountry(), pageable);

        if (!propertyPage.getContent().isEmpty()) {
            return PropertyMapper.toPropertyDtoPage(propertyPage);
        }

        throw new EntityNotFoundException("Properties don't found based on these search criteria");
    }

    @Override
    public PropertyDetailsDto getPropertyById(String id) {
        log.info("Get property with id: {}", id);

        Optional<Property> property = propertyRepository.findById(id);

        if (property.isPresent()) {
            return modelMapper.map(property.get(), PropertyDetailsDto.class);
        }
        throw new EntityNotFoundException("Property with id " + id + " doesn't exist");
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
    public void deleteProperty(String id) {
        log.info("Delete property with id: {}", id);

        Optional<Property> property = propertyRepository.findById(id);

        if (property.isEmpty()) {
            throw new EntityNotFoundException("Property with id " + id + " doesn't exist");
        }

        propertyRepository.deleteById(id);

        outboxHelper.saveOutboxMessage(property.get());
    }

    @Override
    public Set<PropertyDto> getPropertiesByOwnerId(String id) {
        log.info("Get user's properties");
        List<Property> propertyList = propertyRepository.findByOwnerId(id);

        if (!propertyList.isEmpty()) {
            return propertyList.stream()
                    .map(propertyServiceHelper::getPropertyDto)
                    .collect(Collectors.toSet());
        }

        throw new EntityNotFoundException("The user with id " + id + " doesn't have properties");
    }
}
