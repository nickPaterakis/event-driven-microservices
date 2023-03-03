package com.booking.reservationservice.service.reservation;

import com.booking.reservationservice.dto.ReservationDto;

import java.util.Set;

public interface ReservationService {

    ReservationDto createReservation(ReservationDto reservationDto);

    Set<ReservationDto> getReservationsByRenterId(String renterId);

    void deleteReservationsByPropertyId(String propertyId);
}