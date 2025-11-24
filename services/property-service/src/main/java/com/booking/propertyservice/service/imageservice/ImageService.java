package com.booking.propertyservice.service.imageservice;

import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImages(MultipartFile[] images, PropertyDetailsDto propertyDetailsDto);
}
