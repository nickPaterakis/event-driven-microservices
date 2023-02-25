package com.booking.reservationservice.domain.outbox.scheduler;

public interface OutboxScheduler {
    void processOutboxMessage();
}
