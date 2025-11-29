package com.booking.userservice.service.userservice.helper;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.userservice.dto.UserDto;
import com.booking.userservice.exception.UserDomainException;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.user.UserRepository;
import com.booking.userservice.service.imageservice.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Helper component for user-related business operations.
 * Focuses on user-specific business logic while delegating
 * image operations to ImageService.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    private final ImageService imageService;
    private final UserRepository userRepository;

    private static final String EMPTY_STRING = "";

    /**
     * Saves a user to the repository.
     *
     * @param user the user to save
     * @return the saved user
     * @throws UserDomainException if the user cannot be saved
     */
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

    /**
     * Updates a user with new information and optionally a new image.
     *
     * @param image the new profile image (optional)
     * @param userDto the user data transfer object with updated information
     * @return the updated user
     * @throws EntityNotFoundException if the user is not found
     */
    @Transactional
    public User updateUser(MultipartFile image, UserDto userDto) {
        String imageUrl = updateUserImage(image, userDto);
        
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userDto.getEmail() + " no found"));
        
        User updatedUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phone(userDto.getPhone())
                .image(imageUrl)
                .build();

        return saveUser(updatedUser);
    }

    /**
     * Updates the user's profile image.
     * 
     * @param image the new image file
     * @param userDto the user information
     * @return the image URL, or empty string if no image provided
     */
    private String updateUserImage(MultipartFile image, UserDto userDto) {
        if (image == null) {
            return EMPTY_STRING;
        }

        String imageDirectoryUrl = imageService.getImageUrl(userDto.getEmail(), image.getOriginalFilename());
        imageService.uploadImage(image, imageDirectoryUrl);

        return imageDirectoryUrl;
    }
}
