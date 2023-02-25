package com.booking.reservationservice.data.outbox.reservation.exception;

public class ReservationOutboxNotFoundException extends RuntimeException {

    public ReservationOutboxNotFoundException(String message) { super(message); }
}
