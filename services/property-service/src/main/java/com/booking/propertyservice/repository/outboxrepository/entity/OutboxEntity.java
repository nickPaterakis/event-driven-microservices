package com.booking.propertyservice.repository.outboxrepository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "outboxevent")
public class OutboxEntity {

    @Id
    private String id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;
}
