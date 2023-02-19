package com.booking.propertyservice.repository.propertyrepository;

import com.booking.propertyservice.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String>, CustomPropertyRepository {
    List<Property> findByOwnerId(String email);
}
