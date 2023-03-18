package com.booking.notificationservice.model;

import com.booking.domain.valueobject.Money;
import lombok.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private LocalDate checkIn;
    private LocalDate checkOut;
    private Money totalPrice;
    private Property property;
    private User renter;
    private User owner;
}