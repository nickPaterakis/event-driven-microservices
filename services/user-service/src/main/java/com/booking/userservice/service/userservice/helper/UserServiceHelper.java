package com.booking.userservice.service.userservice.helper;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.userservice.dto.UserDto;
import com.booking.userservice.exception.UserDomainException;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    private final Storage storage;
    private final UserRepository userRepository;
    @Value("${user-service.gcp.bucket.name}")
    private String bucketName;

    private static final String EMPTY_STRING = "";

    @SneakyThrows
    public UserDto extractUserDtoFromJson(String userDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(userDto, UserDto.class);
    }

    public String getImageCloudDirectoryUrl(String userEmail, String imageName) {
        return "images/users/" + userEmail + "/" + imageName;
    }

    @SneakyThrows
    public void uploadImage(MultipartFile file, String imageDirectoryUrl) {
        BlobId blobId = BlobId.of(bucketName, imageDirectoryUrl);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] data = file.getBytes();
        storage.create(blobInfo, data);
    }

    public String updateImage(MultipartFile image, UserDto userDto) {
        if (image == null) {
            return EMPTY_STRING;
        }

        String imageDirectoryUrl = getImageCloudDirectoryUrl(userDto.getEmail(), image.getOriginalFilename());
        uploadImage(image, imageDirectoryUrl);

        return imageDirectoryUrl;
    }

    @Transactional
    public User saveUser(User user) {
        User userResult = userRepository.save(user);
        if (userResult == null) {
            log.error("Could not save user.");
            throw new UserDomainException("Could not save user.");
        }
        log.info("User is saved with id: {}", userResult.getId());
        return userResult;
    }

    @Transactional
    public User updateUser(MultipartFile image, UserDto userDto) {
        String imageUrl = updateImage(image, userDto);
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userDto.getEmail() + " no found"));

        if (imageUrl.isEmpty()) {
            imageUrl = user.getImage();
        }

        User updatedUser =  User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phone(userDto.getPhone())
                .image(imageUrl)
                .build();

        return saveUser(updatedUser);
    }
}
