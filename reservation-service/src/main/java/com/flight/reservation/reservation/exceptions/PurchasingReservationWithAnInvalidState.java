package com.flight.reservation.reservation.exceptions;

import com.flight.reservation.reservation.domain.ReservationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PurchasingReservationWithAnInvalidState extends RuntimeException {

    public PurchasingReservationWithAnInvalidState(ReservationStatus reservationStatus){
        super(String.format("You can't purchase a reservation with %s status", reservationStatus.name()));
    }
}
