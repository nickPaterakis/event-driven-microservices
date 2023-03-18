package com.booking.reservationservice.service.reservation;

import com.booking.reservationservice.dto.ReservationDto;

import java.util.Set;

public interface ReservationService {

    ReservationDto createReservation(ReservationDto reservationDto);

    void cancelReservation(String reservationId);

    Set<ReservationDto> getReservationsByRenterId(String renterId);

    void deleteReservationsByPropertyId(String propertyId);
}