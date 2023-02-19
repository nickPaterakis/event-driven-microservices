package com.booking.reservationservice.domain.publisher;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;

import java.util.function.BiConsumer;

public interface ReservationEventPublisher {

    void publish(ReservationOutboxEvent reservationOutboxEvent,
                 BiConsumer<ReservationOutboxEvent, OutboxStatus> outboxCallback);
}
