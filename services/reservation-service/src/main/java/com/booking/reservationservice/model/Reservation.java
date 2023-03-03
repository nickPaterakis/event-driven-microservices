package com.booking.reservationservice.model;

import com.booking.domain.model.AggregateRoot;
import com.booking.domain.valueobject.Money;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reservation extends AggregateRoot<String> {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Property property;
    private Money price;
    private User renter;
    private User owner;

    @Builder
    public Reservation(String id, LocalDate checkIn, LocalDate checkOut, Property property, Money price, User renter, User owner) {
        super.setId(id);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.property = property;
        this.price = price;
        this.renter = renter;
        this.owner = owner;
    }
}
