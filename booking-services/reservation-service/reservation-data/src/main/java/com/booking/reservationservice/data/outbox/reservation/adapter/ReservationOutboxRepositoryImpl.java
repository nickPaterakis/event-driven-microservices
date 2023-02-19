package com.booking.reservationservice.data.outbox.reservation.adapter;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.data.outbox.reservation.exception.ReservationOutboxNotFoundException;
import com.booking.reservationservice.data.outbox.reservation.mapper.ReservationOutboxDataAccessMapper;
import com.booking.reservationservice.data.outbox.reservation.repository.ReservationOutboxMongoRepository;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.domain.repository.ReservationOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationOutboxRepositoryImpl implements ReservationOutboxRepository {

    private final ReservationOutboxMongoRepository reservationOutboxMongoRepository;
    private final ReservationOutboxDataAccessMapper reservationOutboxDataAccessMapper;

    @Override
    public ReservationOutboxEvent save(ReservationOutboxEvent reservationOutboxEvent) {
        return reservationOutboxDataAccessMapper
                .reservationOutboxEntityToReservationCreatedOutboxMessage(reservationOutboxMongoRepository
                        .save(reservationOutboxDataAccessMapper
                                .reservationCreatedOutboxMessageToReservationOutboxEntity
                                        (reservationOutboxEvent)));
    }

    @Override
    public Optional<List<ReservationOutboxEvent>> findByOutboxTypeIn(OutboxStatus... outboxStatus) {
        return Optional.of(reservationOutboxMongoRepository.findByOutboxStatusIn(Arrays.asList(outboxStatus))
                .orElseThrow(() -> new ReservationOutboxNotFoundException("Reservation outbox object " +
                        "could not be found for outbox status " + outboxStatus.toString()))
                .stream()
                .map(reservationOutboxDataAccessMapper::reservationOutboxEntityToReservationCreatedOutboxMessage)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {
        reservationOutboxMongoRepository.deleteByOutboxStatus(outboxStatus);
    }
}
