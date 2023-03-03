package com.booking.reservationservice.repository.outbox;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.exception.ReservationOutboxNotFoundException;
import com.booking.reservationservice.outbox.model.ReservationOutboxEvent;
import com.booking.reservationservice.repository.outbox.entity.ReservationOutboxEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationOutboxRepositoryImpl implements ReservationOutboxRepository {

    private final ReservationOutboxMongoRepository reservationOutboxMongoRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReservationOutboxEvent save(ReservationOutboxEvent reservationOutboxEvent) {
        ReservationOutboxEntity reservationOutboxEntity = reservationOutboxMongoRepository
                .save(modelMapper.map(reservationOutboxEvent, ReservationOutboxEntity.class));
        return modelMapper.map(reservationOutboxEntity, ReservationOutboxEvent.class);
    }

    @Override
    public List<ReservationOutboxEvent> findByOutboxTypeIn(OutboxStatus... outboxStatus) {
        return reservationOutboxMongoRepository.findByOutboxStatusIn(Arrays.asList(outboxStatus))
                .orElseThrow(() -> new ReservationOutboxNotFoundException("Reservation outbox object " +
                        "could not be found for outbox status " + Arrays.toString(outboxStatus)))
                .stream()
                .map(reservationOutboxEntity -> modelMapper.map(reservationOutboxEntity, ReservationOutboxEvent.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {
        reservationOutboxMongoRepository.deleteByOutboxStatus(outboxStatus);
    }
}
