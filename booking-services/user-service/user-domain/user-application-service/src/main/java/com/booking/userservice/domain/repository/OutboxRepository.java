package com.booking.userservice.domain.repository;

import com.booking.userservice.domain.outbox.model.UserOutboxEvent;

public interface OutboxRepository {

    UserOutboxEvent save(UserOutboxEvent userOutboxEvent);

    void delete(UserOutboxEvent userOutboxEvent);
}
