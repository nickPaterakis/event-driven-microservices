package com.booking.reservationservice.domain.repository;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;

import java.util.List;
import java.util.Optional;

public interface ReservationOutboxRepository {

    ReservationOutboxEvent save(ReservationOutboxEvent reservationOutboxEvent);

    Optional<List<ReservationOutboxEvent>> findByOutboxTypeIn(OutboxStatus... outboxStatus);

    void deleteByOutboxStatus(OutboxStatus outboxStatus);
}
