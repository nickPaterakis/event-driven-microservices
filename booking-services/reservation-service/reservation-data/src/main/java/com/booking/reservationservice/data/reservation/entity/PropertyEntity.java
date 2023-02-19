package com.booking.reservationservice.data.reservation.entity;

import com.booking.domain.valueobject.Money;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEntity {

    private String id;
    private String title;
    private String propertyType;
    private String country;
    private String location;
    private Money pricePerNight;
}
