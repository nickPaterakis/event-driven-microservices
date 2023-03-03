package com.booking.userservice.outbox.entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOutboxEvent {

    private String id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;
}
