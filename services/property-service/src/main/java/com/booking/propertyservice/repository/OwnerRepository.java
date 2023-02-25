package com.booking.propertyservice.repository;

import com.booking.propertyservice.model.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerRepository extends MongoRepository<Owner, String> {

    Optional<Owner> findByEmail(String email);
}
