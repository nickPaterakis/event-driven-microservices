package com.booking.reservationservice.outbox.model;

import com.booking.domain.model.reservation.ReservationStatus;
import com.booking.domain.valueobject.Money;
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
    private Money totalPrice;
    private ReservationStatus reservationStatus;
    private PropertyPayload property;
    private UserPayload renter;
    private UserPayload owner;
}
