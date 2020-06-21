package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.service.request.FlightRequest;
import com.flight.reservation.reservation.service.response.FlightResponse;
import com.flight.reservation.reservation.service.response.FlightResponseSerializable;

import java.time.ZoneId;
import java.util.Date;

public class FlightMapper {

    public static FlightResponse map(Flight flight){
        if (flight == null) return null;
        return new FlightResponse(flight.getId(),
                flight.getNumber(),
                flight.getCapacity(),
                flight.getDepartureDate(),
                flight.getDepartureTime(),
                flight.getArrivalDate(),
                flight.getArrivalTime(),
                AirlineMapper.map(flight.getAirline()),
                AirportMapper.map(flight.getDepartureAirport()),
                AirportMapper.map(flight.getArrivalAirport()));
    }

    public static Flight map(FlightRequest flightRequest) {
        if (flightRequest == null) return null;
        Airline airline = AirlineMapper.map(flightRequest.getAirlineRequest());
        Airport departureAirport = AirportMapper.map(flightRequest.getDepartureAirport());
        Airport arrivalAirport = AirportMapper.map(flightRequest.getArrivalAirport());
        return new Flight(flightRequest.getNumber(), flightRequest.getCapacity(), flightRequest.getDepartureDate(), flightRequest.getDepartureTime(), flightRequest.getArrivalDate(), flightRequest.getArrivalTime(), airline, departureAirport, arrivalAirport);
    }

    public static FlightResponseSerializable toSerializableObject(Flight flight){
        if (flight == null) return null;
        return new FlightResponseSerializable(flight.getId(),
                flight.getNumber(),
                flight.getCapacity(),
                Date.from(flight.getDepartureDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                flight.getDepartureTime().toString(),
                Date.from(flight.getArrivalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                flight.getArrivalTime().toString(),
                AirlineMapper.map(flight.getAirline()),
                AirportMapper.map(flight.getDepartureAirport()),
                AirportMapper.map(flight.getArrivalAirport()));
    }

}
