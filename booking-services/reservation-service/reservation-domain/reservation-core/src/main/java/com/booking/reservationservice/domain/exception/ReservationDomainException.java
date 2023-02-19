package com.booking.reservationservice.domain.exception;

import com.booking.domain.exception.DomainException;

public class ReservationDomainException extends DomainException {

    public ReservationDomainException(String message) {
        super(message);
    }

    public ReservationDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
