package com.flight.reservation.email.exception;

public class ReservationWithAnEmptyFlightException extends RuntimeException {

    public ReservationWithAnEmptyFlightException(){
        super("Reservation with an empty flight list encountered");
    }
}
