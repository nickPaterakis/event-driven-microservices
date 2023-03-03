package com.booking.reservationservice.exception;

public class ReservationOutboxNotFoundException extends RuntimeException {

    public ReservationOutboxNotFoundException(String message) { super(message); }
}
