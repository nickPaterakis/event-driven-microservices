package com.booking.propertyservice.service.imageservice;

import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Storage storage;

    @Value("${property-service.gcp.bucket.name}")
    private String bucketName;

    @Override
    public void saveImages(MultipartFile[] images, PropertyDetailsDto propertyDetailsDto) {
        String imageDirectoryUrl = getImageCloudDirectoryUrl(propertyDetailsDto);

        sendPropertyImagesToCloud(images, imageDirectoryUrl);

        setImagesUrls(imageDirectoryUrl, propertyDetailsDto, images);
    }

    private void sendPropertyImagesToCloud(MultipartFile[] files, String imageDirectoryUrl) {
        Arrays.asList(files).forEach(image -> sendImageToCloud(image, imageDirectoryUrl));
    }

    @SneakyThrows
    private void sendImageToCloud(MultipartFile file, String imageDirectoryUrl) {
        BlobId blobId = BlobId.of(bucketName, imageDirectoryUrl + "/" + file.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] data = file.getBytes();
        storage.create(blobInfo, data);
    }

    private String getImageCloudDirectoryUrl(PropertyDetailsDto propertyDetailsDto) {
        return "images/properties/" + propertyDetailsDto.getAddress().getCountry() + "/" +
                propertyDetailsDto.getOwner().getEmail() + "/" + UUID.randomUUID();
    }

    private void setImagesUrls(String imageCloudDirectory, PropertyDetailsDto propertyDetailsDto,
            MultipartFile[] files) {
        Set<String> images = new HashSet<>();

        Arrays.asList(files).forEach(file -> images.add(imageCloudDirectory + "/" + file.getOriginalFilename()));

        propertyDetailsDto.setImages(images);
    }
}
