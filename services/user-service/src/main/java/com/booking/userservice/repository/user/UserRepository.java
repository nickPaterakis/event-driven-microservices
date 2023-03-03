package com.booking.userservice.repository.user;

import com.booking.userservice.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    User save(User user);
}
