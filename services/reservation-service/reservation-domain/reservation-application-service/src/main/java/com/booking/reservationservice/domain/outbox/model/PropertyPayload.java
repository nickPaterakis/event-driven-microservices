package com.booking.reservationservice.domain.outbox.model;

import com.booking.domain.valueobject.Money;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyPayload {

    private String id;
    private String title;
    private String propertyType;
    private String country;
    private String location;
    private Money pricePerNight;
}
