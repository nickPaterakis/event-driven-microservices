package com.booking.propertyservice.service.propertyservice.helper;

import com.booking.propertyservice.dto.response.OwnerDto;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.model.Owner;
import com.booking.propertyservice.model.Property;
import com.booking.propertyservice.repository.OwnerRepository;
import com.booking.propertyservice.repository.propertyrepository.PropertyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
@RequiredArgsConstructor
public class PropertyServiceHelper {

    private final Storage storage;
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    private static final int FIRST_IMAGE = 0;

    public PropertyDetailsDto saveProperty(PropertyDetailsDto propertyDetailsDto) {
        Property property = modelMapper.map(propertyDetailsDto, Property.class);
        return modelMapper.map(propertyRepository.save(property), PropertyDetailsDto.class);
    }

    public void saveOwner(OwnerDto owner) {
        Optional<Owner> optionalOwner = ownerRepository.findByEmail(owner.getEmail());
        if (optionalOwner.isEmpty()) {
            ownerRepository.save(modelMapper.map(owner, Owner.class));
        }
    }

    public void saveImages(MultipartFile[] images, PropertyDetailsDto propertyDetailsDto) {
        String imageDirectoryUrl = getImageCloudDirectoryUrl(propertyDetailsDto);

        sendPropertyImagesToCloud(images, imageDirectoryUrl);

        setImagesUrls(imageDirectoryUrl, propertyDetailsDto, images);
    }

    @SneakyThrows
    public PropertyDetailsDto extractPropertyDetailsDtoFromJson(String propertyDetailsDtoJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(propertyDetailsDtoJson, PropertyDetailsDto.class);
    }

    public void sendPropertyImagesToCloud(MultipartFile[] files, String imageDirectoryUrl) {
        Arrays.asList(files).forEach(image -> sendImageToCloud(image, imageDirectoryUrl));
    }

    @SneakyThrows
    private void sendImageToCloud(MultipartFile file, String imageDirectoryUrl) {
        BlobId blobId = BlobId.of("booking-ms", imageDirectoryUrl + "/" + file.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] data = file.getBytes();
        storage.create(blobInfo, data);
    }

    private String getImageCloudDirectoryUrl(PropertyDetailsDto propertyDetailsDto) {
        return "images/properties/" + propertyDetailsDto.getAddress().getCountry() + "/" +
                propertyDetailsDto.getOwner().getEmail() + "/" + UUID.randomUUID().toString();
    }

    private void setImagesUrls(String imageCloudDirectory, PropertyDetailsDto propertyDetailsDto, MultipartFile[] files) {
        Set<String> images = new HashSet<>();

        Arrays.asList(files).forEach(file ->
                images.add(imageCloudDirectory + "/" + file.getOriginalFilename()));

        propertyDetailsDto.setImages(images);
    }

    public PropertyDto getPropertyDto(Property property) {
        PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
        propertyDto.setImage(new ArrayList<>(property.getImages()).get(FIRST_IMAGE));
        return propertyDto;
    }
}
