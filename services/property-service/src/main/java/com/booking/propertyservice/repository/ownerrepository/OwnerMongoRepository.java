package com.booking.propertyservice.repository.ownerrepository;

import com.booking.propertyservice.repository.propertyrepository.entity.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerMongoRepository extends MongoRepository<OwnerEntity, String> {

}
