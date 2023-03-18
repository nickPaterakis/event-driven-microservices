package com.booking.propertyservice.service.propertyservice;


import com.booking.propertyservice.dto.request.SearchCriteria;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.response.PropertyPageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {

    PropertyPageDto searchProperties(SearchCriteria searchCriteria);

    PropertyDetailsDto getPropertyById(String id);

    PropertyDetailsDto createProperty(MultipartFile[] images, String property);

    void deleteProperty(String id);

    List<PropertyDto> getPropertiesByOwnerId(String email);
}
