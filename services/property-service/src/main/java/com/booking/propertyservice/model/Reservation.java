package com.booking.propertyservice.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private String id;
    private String reservationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String propertyId;
    private String country;
}
