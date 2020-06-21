package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirlineNotFound extends NotFoundException{
    public AirlineNotFound() {
        super("Airline not found");
    }
}