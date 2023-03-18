package com.booking.notificationservice.model;

import com.booking.domain.valueobject.Money;
import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    private String id;
    private String title;
    private String propertyType;
    private Money pricePerNight;
    private String country;
    private String location;
}
