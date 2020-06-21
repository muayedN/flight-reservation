package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.service.FlightService;
import com.flight.reservation.reservation.service.response.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value="/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    private final String DEFAULT_PAGE_NO = "0";
    private final String DEFAULT_PAGE_SIZE = "10";

    /**
     * Get all flights with paging
     *
     * @return  Page<FlightResponse>
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public Page<FlightResponse> getAllFlights(Pageable pageable) {
        return flightService.findAll(pageable);
    }

    /**
     * Get Flight by Id
     * @param id    Flight Id
     * @return FlightResponse
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public FlightResponse getFlightById(@PathVariable Integer id) {
        return flightService.getFlightById(id);
    }

    /**
     * Create a New Flight
     * @param flight    the new flight object
     * @return  FlightResponse
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public FlightResponse createNewFlight(@RequestBody Flight flight) {
        return flightService.save(flight);
    }

    /**
     * Update a Flight
     * @param id        Flight id
     * @param flight    the updated flight object
     * @return FlightResponse
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public FlightResponse updateFlight(@PathVariable Integer id,
                                       @RequestBody Flight flight) {
        flight.setId(id);
        return flightService.save(flight);
    }

    /**
     * Delete a Flight
     * @param id    Flight Id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteFlight(@PathVariable Integer id) {
        flightService.delete(id);
    }

    /**Interger
     * View list of flights between a departure and destination for a date
     * @param departAirportCode the departure airport code
     * @param destAirportCode   the destination airport code
     * @param date  is the departure date of Flight
     * @return Page<FlightResponse>
     */
    @GetMapping(params={"departAirportCode", "destAirportCode", "date"})
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public Page<FlightResponse> getFlightsByAirports(@RequestParam String departAirportCode,
                                                     @RequestParam String destAirportCode,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                     Pageable pageable) {
        return flightService.getFlights(departAirportCode, destAirportCode, date, pageable);
    }
}
