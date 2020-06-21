package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedReservationAccessException extends RuntimeException {

    public UnAuthorizedReservationAccessException(String message){
        super(message);
    }

    public UnAuthorizedReservationAccessException(){
        super("UnAuthorized access attempt to reservation");
    }
}
