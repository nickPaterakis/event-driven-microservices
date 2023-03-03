package com.booking.userservice.repository.outbox.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "outboxevent")
public class OutboxEntity {

    @Id
    private String id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;
}
