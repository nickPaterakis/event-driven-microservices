package com.booking.userservice.domain.service.userservice;

import com.booking.userservice.domain.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String userDto, MultipartFile image);
}
