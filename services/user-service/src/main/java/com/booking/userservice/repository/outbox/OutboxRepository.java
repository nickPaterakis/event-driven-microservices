package com.booking.userservice.repository.outbox;


import com.booking.userservice.outbox.entity.UserOutboxEvent;

public interface OutboxRepository {

    UserOutboxEvent save(UserOutboxEvent userOutboxEvent);

    void delete(UserOutboxEvent userOutboxEvent);
}
