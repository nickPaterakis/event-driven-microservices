package com.booking.reservationservice.domain.outbox.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEventPayload {

    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private PropertyPayload property;
    private UserPayload renter;
    private UserPayload owner;
}
