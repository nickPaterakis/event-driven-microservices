package com.booking.userservice.exception;

import com.booking.domain.exception.DomainException;

public class UserDomainException extends DomainException {

    public UserDomainException(String message) {
        super(message);
    }
}
