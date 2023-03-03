package com.booking.reservationservice.outbox.model;

import com.booking.domain.model.outbox.OutboxStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOutboxEvent {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private ReservationEventPayload payload;
    private OutboxStatus outboxStatus;
    private int version;
}
