package com.booking.propertyservice.repository.reservationrepository;

import com.booking.propertyservice.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {

    List<String>  findReservedPropertiesIds(String country, LocalDate checkIn, LocalDate checkOut);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);

}
