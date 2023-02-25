package com.booking.reservationservice.domain.repository;

import com.booking.reservationservice.domain.model.entity.Reservation;

import java.util.Optional;
import java.util.Set;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Set<Reservation> findReservationsByRenterId(String renterId);

    void deleteReservationsByPropertyId(String propertyId);
}
