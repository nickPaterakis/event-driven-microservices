package com.booking.propertyservice.dto.mapper;

import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyDtoPage;
import com.booking.propertyservice.model.Property;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyDtoPage toPropertyDtoPage(Page<Property> propertyPage) {
        List<PropertyDto> propertyDto = propertyPage.get().map(property ->
                new PropertyDto().setPropertyType(property.getPropertyType())
                        .setImage(new ArrayList<>(property.getImages()).get(0))
                        .setCountry(property.getAddress().getCountry())
                        .setId(property.getId())
                        .setAmenities(property.getAmenities())
                        .setDescription(property.getDescription())
                        .setTitle(property.getTitle())
                        .setBathNumber(property.getBathNumber())
                        .setGuestSpace(property.getGuestSpace())
                        .setMaxGuestNumber(property.getMaxGuestNumber())
                        .setPricePerNight(property.getPricePerNight()))
                .collect(Collectors.toUnmodifiableList());

        return new PropertyDtoPage().setTotalElements(propertyPage.getTotalElements())
                .setProperties(propertyDto);
    }
}
