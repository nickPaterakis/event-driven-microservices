package com.booking.propertyservice.service.propertyservice;


import com.booking.propertyservice.dto.request.SearchCriteria;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyDtoPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface PropertyService {

    PropertyDtoPage searchProperties(SearchCriteria searchCriteria);

    PropertyDetailsDto getPropertyById(String id);

    PropertyDetailsDto createProperty(MultipartFile[] images, String property);

    void deleteProperty(String id);

    Set<PropertyDto> getPropertiesByOwnerId(String email);

}
