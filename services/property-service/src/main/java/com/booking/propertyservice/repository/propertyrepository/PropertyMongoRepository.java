package com.booking.propertyservice.repository.propertyrepository;

import com.booking.propertyservice.repository.propertyrepository.entity.PropertyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyMongoRepository extends MongoRepository<PropertyEntity, String>, CustomPropertyRepository {

    Optional<List<PropertyEntity>> findByOwnerId(String email);
}
