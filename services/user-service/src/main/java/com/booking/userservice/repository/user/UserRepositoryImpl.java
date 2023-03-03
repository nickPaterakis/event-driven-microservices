package com.booking.userservice.repository.user;

import com.booking.userservice.model.User;
import com.booking.userservice.repository.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMongoRepository userMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userMongoRepository.findByEmail(email).map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMongoRepository.save(modelMapper.map(user, UserEntity.class));
        return modelMapper.map(userEntity, User.class);
    }
}
