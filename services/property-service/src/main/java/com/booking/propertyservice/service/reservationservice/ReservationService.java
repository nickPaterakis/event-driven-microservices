package com.booking.propertyservice.service.reservationservice;

import com.booking.domain.event.reservation.ReservationEvent;

public interface ReservationService {

    void createReservation(ReservationEvent reservationEvent);
}
