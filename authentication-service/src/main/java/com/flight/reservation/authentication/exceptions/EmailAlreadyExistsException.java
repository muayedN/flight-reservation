package com.flight.reservation.authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends ReservationAuthenticationException{

    public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
