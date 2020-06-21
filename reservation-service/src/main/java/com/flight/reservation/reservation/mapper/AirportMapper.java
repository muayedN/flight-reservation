package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.service.request.AirportRequest;
import com.flight.reservation.reservation.service.response.AddressResponse;
import com.flight.reservation.reservation.service.response.AirportResponse;

public class AirportMapper {
    public static AirportResponse map(Airport airport) {
        if (airport == null) return null;
        AddressResponse addressResponse = AddressMapper.map(airport.getAddress());
        return new AirportResponse(airport.getId(), airport.getName(), airport.getCode(), addressResponse);
    }

    public static Airport map(AirportRequest airportRequest) {
        if (airportRequest == null) return null;
        return new Airport(airportRequest.getName(), airportRequest.getCode());
    }
}
