package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.mapper.FlightMapper;
import com.flight.reservation.reservation.repo.FlightRepo;
import com.flight.reservation.reservation.service.FlightService;
import com.flight.reservation.reservation.service.response.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional()
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Override
    public Page<FlightResponse> findAll(Pageable pageable) {
        return flightRepo.findAll(pageable)
                .map(FlightMapper::map);
    }

    @Override
    public FlightResponse getFlightById(Integer flightId) {
        return FlightMapper.map(flightRepo.findById(flightId).orElse(null));
    }

    public Page<FlightResponse> getFlights(String departAirportCode,
                                           String destAirportCode,
                                           LocalDate date,
                                           Pageable pageable) {
        return flightRepo.getFlights(departAirportCode, destAirportCode, date, pageable)
                .map(FlightMapper::map);
    }

    public FlightResponse save(Flight flight) {
        return FlightMapper.map(flightRepo.save(flight));
    }

    public void delete(int id) {
        flightRepo.deleteById(id);
    }
}