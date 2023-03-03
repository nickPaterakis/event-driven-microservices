package com.booking.reservationservice.repository.outbox;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;

import java.util.List;
import java.util.Optional;

public interface ReservationOutboxRepository {

    ReservationOutboxEvent save(ReservationOutboxEvent reservationOutboxEvent);

    List<ReservationOutboxEvent> findByOutboxTypeIn(OutboxStatus... outboxStatus);

    void deleteByOutboxStatus(OutboxStatus outboxStatus);
}
