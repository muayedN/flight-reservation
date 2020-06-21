package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.service.request.AirportRequest;
import com.flight.reservation.reservation.service.response.AirportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirportService {

	public Page<AirportResponse> getAllAirports(Pageable pageable);

	public AirportResponse save(AirportRequest airportRequest);

	public AirportResponse findOne(int id);

	public void delete(int id);

	public AirportResponse update(AirportRequest airportRequest, int id);

}
