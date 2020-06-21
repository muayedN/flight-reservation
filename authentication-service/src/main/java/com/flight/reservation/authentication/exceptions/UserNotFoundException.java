package com.flight.reservation.authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ReservationAuthenticationException {
    public UserNotFoundException() {
        super("User not found");
    }
}
