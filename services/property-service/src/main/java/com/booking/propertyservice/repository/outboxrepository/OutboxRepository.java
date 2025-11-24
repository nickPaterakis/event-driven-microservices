package com.booking.propertyservice.repository.outboxrepository;

import com.booking.propertyservice.outbox.model.Outbox;

public interface OutboxRepository  {

    Outbox save(Outbox outbox);

    void delete(Outbox outbox);
}
