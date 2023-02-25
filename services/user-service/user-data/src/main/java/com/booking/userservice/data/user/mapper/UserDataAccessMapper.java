package com.booking.userservice.data.user.mapper;

import com.booking.userservice.data.user.entity.UserEntity;
import com.booking.userservice.domain.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    public UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .image(user.getImage())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phone(userEntity.getPhone())
                .image(userEntity.getImage())
                .build();
    }
}
