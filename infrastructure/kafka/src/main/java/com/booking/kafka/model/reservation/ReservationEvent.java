package com.booking.kafka.model.reservation;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEvent {
    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Property property;
    private User renter;
    private User owner;
}
