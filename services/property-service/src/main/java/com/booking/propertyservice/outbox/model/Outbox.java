package com.booking.propertyservice.outbox.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Outbox {

    private String id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;
}
