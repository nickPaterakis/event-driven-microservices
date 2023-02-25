package com.booking.reservationservice.domain.outbox.model;

import com.booking.domain.model.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReservationOutboxEvent {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private ReservationEventPayload payload;
    private OutboxStatus outboxStatus;
    private int version;

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public void setOutboxStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
    }
}
