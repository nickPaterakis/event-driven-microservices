package com.booking.userservice.data.user.repository;

import com.booking.userservice.data.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}
