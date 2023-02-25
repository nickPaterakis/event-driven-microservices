package com.booking.reservationservice.data.outbox.reservation.mapper;

import com.booking.reservationservice.data.outbox.reservation.entity.ReservationOutboxEntity;
import com.booking.reservationservice.domain.outbox.model.ReservationOutboxEvent;
import org.springframework.stereotype.Component;

@Component
public class ReservationOutboxDataAccessMapper {

    public ReservationOutboxEntity reservationCreatedOutboxMessageToReservationOutboxEntity(ReservationOutboxEvent reservationOutboxEvent) {
        return ReservationOutboxEntity.builder()
                .id(reservationOutboxEvent.getId())
                .outboxStatus(reservationOutboxEvent.getOutboxStatus())
                .payload(reservationOutboxEvent.getPayload())
                .createdAt(reservationOutboxEvent.getCreatedAt())
                .processedAt(reservationOutboxEvent.getProcessedAt())
                .version(reservationOutboxEvent.getVersion())
                .build();
    }

    public ReservationOutboxEvent reservationOutboxEntityToReservationCreatedOutboxMessage(ReservationOutboxEntity reservationOutboxEntity) {
        return ReservationOutboxEvent.builder()
                .id(reservationOutboxEntity.getId())
                .outboxStatus(reservationOutboxEntity.getOutboxStatus())
                .payload(reservationOutboxEntity.getPayload())
                .createdAt(reservationOutboxEntity.getCreatedAt())
                .processedAt(reservationOutboxEntity.getProcessedAt())
                .version(reservationOutboxEntity.getVersion())
                .build();
    }
}
