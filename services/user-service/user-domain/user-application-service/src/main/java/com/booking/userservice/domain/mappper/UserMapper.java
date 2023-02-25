package com.booking.userservice.domain.mappper;

import com.booking.userservice.domain.dto.UserDto;
import com.booking.userservice.domain.model.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .image(userDto.getImage())
                .build();
    }

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .image(user.getImage())
                .build();
    }
}
