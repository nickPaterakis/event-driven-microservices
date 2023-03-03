package com.booking.reservationservice.repository.reservation;

import com.booking.reservationservice.model.Reservation;

import java.util.Set;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Set<Reservation> findReservationsByRenterId(String renterId);

    void deleteReservationsByPropertyId(String propertyId);
}
