package com.booking.userservice.domain.service.userservice;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.userservice.domain.dto.UserDto;
import com.booking.userservice.domain.mappper.UserMapper;
import com.booking.userservice.domain.model.entity.User;
import com.booking.userservice.domain.outbox.helper.OutboxHelper;
import com.booking.userservice.domain.repository.UserRepository;
import com.booking.userservice.domain.service.userservice.helper.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final OutboxHelper outboxHelper;
    private final ModelMapper modelMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Get user by email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " no found"));
        return UserMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Create user with email: {}", userDto.getEmail());
        User user = UserMapper.userDtoToUser(userDto);
        return UserMapper.userToUserDto(userServiceHelper.saveUser(user));
    }

    @Override
    @Transactional
    public UserDto updateUser(String userDtoJson, MultipartFile image) {
        UserDto userDto = userServiceHelper.extractUserDtoFromJson(userDtoJson);

        log.info("Update user with email: {}", userDto.getEmail());

        if (Objects.nonNull(image)) {
            userDto.setImage(userServiceHelper.updateImage(image, userDto));
        }

        User updatedUser = userServiceHelper.updateUser(userDto);

        outboxHelper.saveOutboxMessage(updatedUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }
}
