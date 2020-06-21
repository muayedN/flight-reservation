package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SavingFailureException extends RuntimeException {
    public SavingFailureException(String message) {
        super(message);
    }
}
