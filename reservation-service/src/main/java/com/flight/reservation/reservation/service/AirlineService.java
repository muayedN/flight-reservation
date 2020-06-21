package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.service.request.AirlineRequest;
import com.flight.reservation.reservation.service.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirlineService {

	public Page<AirlineResponse> getAllAirlines(Pageable pageable);

	public AirlineResponse save(AirlineRequest airlineRequest);

	public AirlineResponse findOne(int id);

	public void delete(int id);

	public AirlineResponse update(AirlineRequest airlineRequest, int id);

	Page<AirlineResponse> getAirlineByDepartureAirport(String code, Pageable pageable);
}
