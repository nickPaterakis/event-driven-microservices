package com.booking.propertyservice.service.reservationservice;

import com.booking.kafka.model.reservation.ReservationEvent;
import com.booking.propertyservice.model.Reservation;

public interface ReservationService {

    void createReservation(ReservationEvent reservationEvent);
}
