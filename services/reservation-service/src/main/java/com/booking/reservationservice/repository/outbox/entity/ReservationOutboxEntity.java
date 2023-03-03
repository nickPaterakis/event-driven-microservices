package com.booking.reservationservice.repository.outbox.entity;

import com.booking.domain.model.outbox.OutboxStatus;
import com.booking.reservationservice.outbox.model.ReservationEventPayload;
import lombok.*;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservations_outbox")
public class ReservationOutboxEntity {

    @MongoId
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private ReservationEventPayload payload;
    private OutboxStatus outboxStatus;
    @Version
    private int version;
}
