package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirportNotFound extends NotFoundException{
    public AirportNotFound() {
        super("Airport not found");
    }
}
