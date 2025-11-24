package com.booking.propertyservice.dto.mapper;

import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyPageDto;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.model.PropertyPage;

import java.util.ArrayList;
import java.util.List;

public class PropertyMapper {

        public static PropertyPageDto toPropertyPageDto(PropertyPage propertyPage) {
                List<PropertyDto> propertyDtos = propertyPage.getProperties().stream()
                                .map(PropertyMapper::toPropertyDto)
                                .toList();

                return PropertyPageDto.builder()
                                .totalElements(propertyPage.getTotalElements())
                                .properties(propertyDtos)
                                .build();
        }

        public static PropertyDto toPropertyDto(Property property) {
                PropertyDto propertyDto = new PropertyDto()
                                .setPropertyType(property.getPropertyType())
                                .setCountry(property.getAddress().getCountry())
                                .setId(property.getId())
                                .setAmenities(property.getAmenities())
                                .setDescription(property.getDescription())
                                .setTitle(property.getTitle())
                                .setBathNumber(property.getBathNumber())
                                .setGuestSpace(property.getGuestSpace())
                                .setMaxGuestNumber(property.getMaxGuestNumber())
                                .setPricePerNight(property.getPricePerNight());

                if (property.getImages() != null && !property.getImages().isEmpty()) {
                        propertyDto.setImage(new ArrayList<>(property.getImages()).get(0));
                }

                return propertyDto;
        }
}
