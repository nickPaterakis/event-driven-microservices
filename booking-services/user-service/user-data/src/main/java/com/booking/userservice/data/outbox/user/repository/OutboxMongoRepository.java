package com.booking.userservice.data.outbox.user.repository;

import com.booking.userservice.data.outbox.user.entity.Outbox;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutboxMongoRepository extends MongoRepository<Outbox, String> {

}
