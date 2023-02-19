package com.booking.userservice.data.user.adapter;

import com.booking.userservice.data.user.entity.UserEntity;
import com.booking.userservice.data.user.mapper.UserDataAccessMapper;
import com.booking.userservice.data.user.repository.UserMongoRepository;
import com.booking.userservice.domain.model.entity.User;
import com.booking.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMongoRepository userMongoRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userMongoRepository.findByEmail(email).map(userDataAccessMapper::userEntityToUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMongoRepository.save(userDataAccessMapper.userToUserEntity(user));
        return userDataAccessMapper.userEntityToUser(userEntity);
    }
}
