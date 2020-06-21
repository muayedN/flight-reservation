package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.service.AirportService;
import com.flight.reservation.reservation.service.request.AirportRequest;
import com.flight.reservation.reservation.service.response.AirportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public AirportController (AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public Page<AirportResponse> getAllAirports(Pageable pageable) {
        Page<AirportResponse> airports = airportService.getAllAirports(pageable);
        return airports;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AirportResponse addAirport(@RequestBody AirportRequest airportRequest) {
        return airportService.save(airportRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public AirportResponse getAirport(@PathVariable int id) {
        return airportService.findOne(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AirportResponse updateAirport(@RequestBody AirportRequest airportRequest, @PathVariable int id) {
        return airportService.update(airportRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAirport(@PathVariable int id) {
        airportService.delete(id);
    }
}
