package com.booking.userservice.domain.outbox.model;

import com.booking.userservice.domain.model.entity.User;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
public class UserOutboxEvent {

    private String id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;
}
