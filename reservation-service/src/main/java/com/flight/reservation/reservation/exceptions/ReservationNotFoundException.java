package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException{

	public ReservationNotFoundException(String message) {
		super(message);
	}

	public ReservationNotFoundException() {
		super("Reservation Not Found Exception");
	}
}
