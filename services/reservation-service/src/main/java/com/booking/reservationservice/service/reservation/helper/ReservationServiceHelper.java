package com.booking.reservationservice.service.reservation.helper;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.exception.ReservationDomainException;
import com.booking.reservationservice.model.Reservation;
import com.booking.reservationservice.outbox.model.ReservationEventPayload;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationServiceHelper {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation saveReservation(Reservation reservation) {
        Reservation reservationResult = reservationRepository.save(reservation);
        if (reservationResult == null) {
            log.error("Could not save reservation.");
            throw new ReservationDomainException("Could not save reservation.");
        }
        log.info("Reservation is saved with id: {}", reservationResult.getId());
        return reservationResult;
    }

    public ReservationOutboxEvent createReservationOutboxMessage(
            ReservationEventPayload reservationEventPayload,
            OutboxStatus outboxStatus) {
        return ReservationOutboxEvent.builder()
                .id(UUID.randomUUID().toString())
                .payload(reservationEventPayload)
                .outboxStatus(outboxStatus)
                .build();
    }
}
