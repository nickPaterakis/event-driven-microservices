package com.booking.propertyservice.repository.reservationrepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomReservationRepository {

    List<String> findReservedProperties(String country, LocalDate checkIn, LocalDate checkOut);
}
