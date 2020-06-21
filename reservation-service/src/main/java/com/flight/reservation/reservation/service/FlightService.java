package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.service.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
	Page<FlightResponse> findAll(Pageable pageable);
	FlightResponse getFlightById(Integer flightId);
	Page<FlightResponse> getFlights(String departAirportCode, String destAirportCode, LocalDate date, Pageable pageable);
	FlightResponse save(Flight flight);
	void delete(int id);
}
