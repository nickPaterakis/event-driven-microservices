package com.booking.propertyservice.repository;

import com.booking.propertyservice.outbox.model.Outbox;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutboxRepository extends MongoRepository<Outbox, String> {

}
