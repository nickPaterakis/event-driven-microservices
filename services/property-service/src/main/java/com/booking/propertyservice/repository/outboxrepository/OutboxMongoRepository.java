package com.booking.propertyservice.repository.outboxrepository;

import com.booking.propertyservice.repository.outboxrepository.entity.OutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutboxMongoRepository extends MongoRepository<OutboxEntity, String> {
}
