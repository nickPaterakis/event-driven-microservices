package com.booking.reservationservice.outbox.scheduler;

public interface OutboxScheduler {
    void processOutboxMessage();
}
