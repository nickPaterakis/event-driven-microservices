package com.booking.domain.event.reservation;

import com.booking.domain.valueobject.Money;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String id;
    private String title;
    private String propertyType;
    private String country;
    private String location;
    private Money pricePerNight;
}
