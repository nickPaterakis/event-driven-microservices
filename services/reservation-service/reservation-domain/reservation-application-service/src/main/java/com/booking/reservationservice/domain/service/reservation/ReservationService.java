package com.booking.reservationservice.domain.service.reservation;

import com.booking.reservationservice.domain.dto.ReservationDto;

import java.util.Set;

public interface ReservationService {

    ReservationDto createReservation(ReservationDto reservationDto);

    Set<ReservationDto> getReservationsByRenterId(String renterId);

    void deleteReservationsByPropertyId(String propertyId);
}