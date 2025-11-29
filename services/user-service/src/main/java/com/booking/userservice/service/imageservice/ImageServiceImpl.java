package com.booking.userservice.service.imageservice;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementation of ImageService for handling Google Cloud Storage operations.
 * Manages image uploads to GCP bucket.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Storage storage;

    @Value("${user-service.gcp.bucket.name}")
    private String bucketName;

    @Override
    @SneakyThrows
    public void uploadImage(MultipartFile file, String directoryUrl) {
        log.info("Uploading image to directory: {}", directoryUrl);
        
        BlobId blobId = BlobId.of(bucketName, directoryUrl);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] data = file.getBytes();
        storage.create(blobInfo, data);
        
        log.info("Image uploaded successfully to: {}", directoryUrl);
    }

    @Override
    public String getImageUrl(String userEmail, String imageName) {
        return "images/users/" + userEmail + "/" + imageName;
    }
}
