package com.booking.propertyservice.dto.mapper;

import com.booking.kafka.model.reservation.ReservationEvent;
import com.booking.propertyservice.model.Reservation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReservationMapper {

    public static Reservation reservationToReservationEvent(ReservationEvent reservationEvent) {
        return Reservation.builder()
                .reservationId(reservationEvent.getId())
                .checkIn(reservationEvent.getCheckIn())
                .checkOut(reservationEvent.getCheckOut())
                .propertyId(reservationEvent.getProperty().getId())
                .country(reservationEvent.getProperty().getCountry())
                .build();
    }
}
