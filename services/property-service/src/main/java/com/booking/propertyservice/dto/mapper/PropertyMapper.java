package com.booking.propertyservice.dto.mapper;

import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyPageDto;
import com.booking.propertyservice.model.PropertyPage;

import java.util.ArrayList;
import java.util.List;

public class PropertyMapper {

    public static PropertyPageDto toPropertyPageDto(PropertyPage propertyPage) {
        List<PropertyDto> propertyDtos = propertyPage.getProperties().stream().map(property ->
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
                .toList();

        return PropertyPageDto.builder()
                .totalElements(propertyPage.getTotalElements())
                .properties(propertyDtos)
                .build();
    }
}
