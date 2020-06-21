package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.exceptions.AirlineNotFound;
import com.flight.reservation.reservation.exceptions.NotFoundException;
import com.flight.reservation.reservation.exceptions.SavingFailureException;
import com.flight.reservation.reservation.mapper.AirlineMapper;
import com.flight.reservation.reservation.repo.AirlineRepo;
import com.flight.reservation.reservation.service.AirlineService;
import com.flight.reservation.reservation.service.request.AirlineRequest;
import com.flight.reservation.reservation.service.response.AirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepo airlineRepo;

    public AirlineServiceImpl(AirlineRepo airlineRepo) {
        this.airlineRepo = airlineRepo;
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {

        List<AirlineResponse> airlineResponses = airlineRepo.findAll().stream().parallel()
                .map(AirlineMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(airlineResponses);
    }

    @Override
    public AirlineResponse save(AirlineRequest airlineRequest) {

        Airline airline = AirlineMapper.map(airlineRequest);
        if(airlineRepo.save(airline) == null) {
            throw new SavingFailureException("Saving failed");
        }
        return AirlineMapper.map(airline);
    }

    @Override
    public AirlineResponse findOne(int id) {

        return AirlineMapper.map(airlineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the Airline")));
    }

    @Override
    public void delete(int id) {

            airlineRepo.deleteById(id);
    }

    @Override
    public AirlineResponse update(AirlineRequest airlineRequest, int id) {

        Airline airline = AirlineMapper.map(airlineRequest);
        airline.setId(airlineRepo.findById(id).orElseThrow(AirlineNotFound::new).getId());
        if(airlineRepo.save(airline) == null) {
            throw new SavingFailureException("Updating failed");
        }
        return AirlineMapper.map(airline);
    }

    @Override
    public Page<AirlineResponse> getAirlineByDepartureAirport(String code, Pageable pageable) {
        List<AirlineResponse> airlineResponses = airlineRepo.getAirlineByDepartureAirport(code, pageable).stream().parallel()
                .map(AirlineMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(airlineResponses);
    }

    private AirlineResponse convertAirlineToAirlineResponse(Airline airline) {
        if (airline == null) return null;
        return new AirlineResponse(airline.getId(), airline.getName(), airline.getCode(), airline.getHistory());
    }
}