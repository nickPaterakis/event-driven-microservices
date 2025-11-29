package com.booking.userservice.service.imageservice;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface for handling image operations.
 * Provides methods for uploading images and generating image URLs.
 */
public interface ImageService {

    /**
     * Uploads an image file to cloud storage.
     *
     * @param file the image file to upload
     * @param directoryUrl the directory URL where the image should be stored
     */
    void uploadImage(MultipartFile file, String directoryUrl);

    /**
     * Generates the cloud storage directory URL for a user's image.
     *
     * @param userEmail the email of the user
     * @param imageName the name of the image file
     * @return the complete directory URL for the image
     */
    String getImageUrl(String userEmail, String imageName);
}
