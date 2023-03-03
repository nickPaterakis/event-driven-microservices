package com.booking.reservationservice.model;

import com.booking.domain.model.BaseEntity;
import com.booking.domain.valueobject.Money;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Property extends BaseEntity<String> {

    private String title;
    private String propertyType;
    private String country;
    private String location;
    private Money pricePerNight;

    @Builder
    public Property(String id, String title, String propertyType, String country, String location, Money pricePerNight) {
        super.setId(id);
        this.title = title;
        this.propertyType = propertyType;
        this.country = country;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }
}
