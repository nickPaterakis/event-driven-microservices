package com.booking.reservationservice.outbox.scheduler;

import com.booking.reservationservice.exception.ReservationDomainException;
import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.repository.outbox.ReservationOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationOutboxHelper {

    private final ReservationOutboxRepository reservationOutboxRepository;

    @Transactional
    public void saveReservationOutboxMessage(ReservationOutboxEvent reservationOutboxEvent) {
        ReservationOutboxEvent response = reservationOutboxRepository.save(reservationOutboxEvent);
        if (response == null) {
            log.error("Could not save ReservationOutboxMessage with outbox id: {}", reservationOutboxEvent.getId());
            throw new ReservationDomainException("Could not save ReservationOutboxMessage with outbox id: " +
                    reservationOutboxEvent.getId());
        }
        log.info("ReservationOutboxMessage saved with outbox id: {}", reservationOutboxEvent.getId());
    }

    @Transactional(readOnly = true)
    public List<ReservationOutboxEvent> getReservationOutboxMessageByOutboxStatus(OutboxStatus... outboxStatus) {
        return reservationOutboxRepository.findByOutboxTypeIn(outboxStatus);
    }

    @Transactional
    public void deleteReservationOutboxMessageByOutboxStatus(OutboxStatus outboxStatus) {
        reservationOutboxRepository.deleteByOutboxStatus(outboxStatus);
    }
}
