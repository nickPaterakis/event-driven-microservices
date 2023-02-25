package com.booking.userservice.domain.repository;

import com.booking.userservice.domain.model.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    User save(User user);
}
