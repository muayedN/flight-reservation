package com.flight.reservation.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidFlightException extends RuntimeException{
	
	public InvalidFlightException(String message) {
		super(message);
	}

}
