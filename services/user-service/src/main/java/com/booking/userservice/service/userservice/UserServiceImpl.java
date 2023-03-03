package com.booking.userservice.service.userservice;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.userservice.dto.UserDto;
import com.booking.userservice.model.User;
import com.booking.userservice.outbox.helper.OutboxHelper;
import com.booking.userservice.repository.user.UserRepository;
import com.booking.userservice.service.userservice.helper.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Create user with email: {}", userDto.getEmail());
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userServiceHelper.saveUser(user), UserDto.class);
    }

    @Override
    @Transactional
    public UserDto updateUser(String userDtoJson, MultipartFile image) {
        UserDto userDto = userServiceHelper.extractUserDtoFromJson(userDtoJson);

        log.info("Update user with email: {}", userDto.getEmail());

        User updatedUser = userServiceHelper.updateUser(image, userDto);

        outboxHelper.saveOutboxMessage(updatedUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }
}
