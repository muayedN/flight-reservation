package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.service.request.AirlineRequest;
import com.flight.reservation.reservation.service.response.AirlineResponse;

public class AirlineMapper {

    public static AirlineResponse map(Airline airline) {
        if (airline == null) return null;
        return new AirlineResponse(airline.getId(), airline.getName(), airline.getCode(), airline.getHistory());
    }

    public static Airline map(AirlineRequest airlineRequest) {
        if (airlineRequest == null) return null;
        return new Airline(airlineRequest.getName(), airlineRequest.getCode(), airlineRequest.getHistory());
    }
}
