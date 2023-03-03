package com.booking.userservice.repository.outbox;

import com.booking.userservice.repository.outbox.entity.OutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

interface OutboxMongoRepository extends MongoRepository<OutboxEntity, String> {

}
