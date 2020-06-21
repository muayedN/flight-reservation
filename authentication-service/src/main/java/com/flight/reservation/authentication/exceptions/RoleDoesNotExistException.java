package com.flight.reservation.authentication.exceptions;

import com.flight.reservation.authentication.domain.RoleCode;

public class RoleDoesNotExistException extends ReservationAuthenticationException{
    public RoleDoesNotExistException(RoleCode roleCode) {
        super(roleCode + "does not exist");
    }
}
