package com.booking.userservice.data.outbox.user.mapper;

import com.booking.userservice.data.outbox.user.entity.Outbox;
import com.booking.userservice.domain.outbox.model.UserOutboxEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OutboxMapper {

    public Outbox userOutboxEventToOutbox(UserOutboxEvent userOutboxEvent) {
        return Outbox.builder()
                .id(userOutboxEvent.getId())
                .aggregateType(userOutboxEvent.getAggregateType())
                .aggregateId(userOutboxEvent.getAggregateId())
                .type(userOutboxEvent.getType())
                .payload(userOutboxEvent.getPayload())
                .build();
    }

    public UserOutboxEvent outboxToUserOutboxEvent(Outbox outbox) {
        return UserOutboxEvent.builder()
                .id(outbox.getId())
                .aggregateType(outbox.getAggregateType())
                .aggregateId(outbox.getAggregateId())
                .type(outbox.getType())
                .payload(outbox.getPayload())
                .build();
    }
}
