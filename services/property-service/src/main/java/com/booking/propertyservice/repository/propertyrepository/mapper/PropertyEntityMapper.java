package com.booking.propertyservice.repository.propertyrepository.mapper;

import com.booking.propertyservice.model.Address;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.model.PropertyPage;
import com.booking.propertyservice.repository.propertyrepository.entity.PropertyEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;

@UtilityClass
public class PropertyEntityMapper {

    public static PropertyPage toPropertyPage(Page<PropertyEntity> propertyEntityPage) {
        List<Property> properties = propertyEntityPage.get().map(propertyEntity ->
                        Property.builder()
                                .propertyType(propertyEntity.getPropertyType())
                                .images(propertyEntity.getImages())
                                .address(Address.builder()
                                        .country(propertyEntity.getAddress().getCountry())
                                        .build())
                                .id(propertyEntity.getId())
                                .amenities(propertyEntity.getAmenities())
                                .description(propertyEntity.getDescription())
                                .title(propertyEntity.getTitle())
                                .bathNumber(propertyEntity.getBathNumber())
                                .guestSpace(propertyEntity.getGuestSpace())
                                .maxGuestNumber(propertyEntity.getMaxGuestNumber())
                                .pricePerNight(propertyEntity.getPricePerNight())
                                .build())
                .toList();

        return PropertyPage.builder()
                .totalElements(propertyEntityPage.getTotalElements())
                .properties(properties)
                .build();
    }
}
