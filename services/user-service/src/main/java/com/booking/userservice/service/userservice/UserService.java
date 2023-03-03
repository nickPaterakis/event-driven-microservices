package com.booking.userservice.service.userservice;

import com.booking.userservice.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String userDto, MultipartFile image);
}
