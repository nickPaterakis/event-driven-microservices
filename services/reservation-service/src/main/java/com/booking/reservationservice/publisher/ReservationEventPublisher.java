package com.booking.reservationservice.publisher;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;

import java.util.function.BiConsumer;

public interface ReservationEventPublisher {

    void publish(ReservationOutboxEvent reservationOutboxEvent,
                 BiConsumer<ReservationOutboxEvent, OutboxStatus> outboxCallback);
}
