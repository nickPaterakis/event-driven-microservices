package com.booking.propertyservice.repository.propertyrepository;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.model.PropertyPage;
import com.booking.propertyservice.repository.propertyrepository.entity.PropertyEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.booking.propertyservice.repository.propertyrepository.mapper.PropertyEntityMapper.toPropertyPage;

@Component
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final PropertyMongoRepository propertyMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Property findPropertyByPropertyId(String propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyMongoRepository.findById(propertyId);

        if (propertyEntity.isEmpty()) {
            throw new EntityNotFoundException("Property with id " + propertyId + " doesn't exist");
        }

        return modelMapper.map(propertyEntity.get(), Property.class);
    }

    @Override
    public List<Property> findPropertiesByOwnerId(String ownerId) {
        Optional<List<PropertyEntity>> propertyEntities = propertyMongoRepository.findByOwnerId(ownerId);

        if (propertyEntities.isEmpty()) {
            throw new EntityNotFoundException("The user with id " + ownerId + " doesn't have properties");
        }

        return propertyEntities.get().stream()
                .map(propertyEntity -> modelMapper.map(propertyEntity, Property.class))
                .toList();
    }

    @Override
    public PropertyPage findUnreservedProperties(List<String> reservedPropertiesIds, Integer guestNumber, String country, Pageable pageable) {
        Page<PropertyEntity> propertyEntityPage = propertyMongoRepository.findUnreservedProperties(reservedPropertiesIds, guestNumber, country, pageable);

        if (propertyEntityPage.getContent().isEmpty()) {
            throw new EntityNotFoundException("Properties don't found based on these search criteria");
        }

        return toPropertyPage(propertyEntityPage);
    }


    @Override
    public void deletePropertyByPropertyId(String propertyId) {
        findPropertyByPropertyId(propertyId);
        propertyMongoRepository.deleteById(propertyId);
    }

    @Override
    public Property saveProperty(Property property) {
        PropertyEntity propertyEntity = modelMapper.map(property, PropertyEntity.class);

        PropertyEntity savedProperty = propertyMongoRepository.save(propertyEntity);

        return modelMapper.map(savedProperty, Property.class);
    }
}
