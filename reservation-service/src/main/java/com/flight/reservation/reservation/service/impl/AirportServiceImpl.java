package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.exceptions.AirportNotFound;
import com.flight.reservation.reservation.exceptions.NotFoundException;
import com.flight.reservation.reservation.exceptions.SavingFailureException;
import com.flight.reservation.reservation.mapper.AirportMapper;
import com.flight.reservation.reservation.repo.AirportRepo;
import com.flight.reservation.reservation.service.AirportService;
import com.flight.reservation.reservation.service.request.AirportRequest;
import com.flight.reservation.reservation.service.response.AirportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepo airportRepo;

    public AirportServiceImpl(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    @Override
    public Page<AirportResponse> getAllAirports(Pageable pageable) {

        List<AirportResponse> airportResponses = airportRepo.findAll().stream().parallel()
                .map(AirportMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(airportResponses);
    }

    @Override
    public AirportResponse save(AirportRequest airportRequest) {

        Airport airport = AirportMapper.map(airportRequest);
        if(airportRepo.save(airport) == null) {
            throw new SavingFailureException("Saving failed");
        }
        return AirportMapper.map(airport);
    }

    @Override
    public AirportResponse findOne(int id) {

        return AirportMapper.map(airportRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the Airport")));
    }

    @Override
    public void delete(int id) {

        airportRepo.deleteById(id);
    }

    @Override
    public AirportResponse update(AirportRequest airportRequest, int id) {

        Airport airport = AirportMapper.map(airportRequest);
        airport.setId(airportRepo.findById(id).orElseThrow(AirportNotFound::new).getId());
        if(airportRepo.save(airport) == null) {
            throw new SavingFailureException("Updating failed");
        }
        return AirportMapper.map(airport);
    }
}
