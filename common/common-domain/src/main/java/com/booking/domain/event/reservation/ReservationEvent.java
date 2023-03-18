package com.booking.domain.event.reservation;

import com.booking.domain.model.reservation.ReservationStatus;
import com.booking.domain.valueobject.Money;
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
    private Money totalPrice;
    private ReservationStatus reservationStatus;
    private Property property;
    private User renter;
    private User owner;
}
