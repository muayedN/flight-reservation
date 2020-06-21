package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.service.AirlineService;
import com.flight.reservation.reservation.service.request.AirlineRequest;
import com.flight.reservation.reservation.service.response.AirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public AirlineController (AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        Page<AirlineResponse> result = airlineService.getAllAirlines(pageable);
        return result;
    }

    @GetMapping(value = "/{airportCode}/departureAirport")
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public Page<AirlineResponse> getAirlineByDepartureAirport(@PathVariable String airportCode, Pageable pageable) {
        Page<AirlineResponse> result = airlineService.getAirlineByDepartureAirport(airportCode, pageable);
        return result;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AirlineResponse addAirline(@RequestBody AirlineRequest airlineRequest) {
        return airlineService.save(airlineRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public AirlineResponse getAirline(@PathVariable int id) {
        return airlineService.findOne(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AirlineResponse updateAirline(@RequestBody AirlineRequest airlineRequest, @PathVariable int id) {
        return airlineService.update(airlineRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAirline(@PathVariable int id) {
        airlineService.delete(id);
    }
}