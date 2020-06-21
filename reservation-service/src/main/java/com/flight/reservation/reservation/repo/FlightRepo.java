package com.flight.reservation.reservation.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flight.reservation.reservation.domain.Flight;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer> {
    @Query(value = "SELECT f FROM Flight f  WHERE f.departureAirport.code = (:departAirportCode) and f.arrivalAirport.code = (:destAirportCode) and f.departureDate = (:date)")
    Page<Flight> getFlights(String departAirportCode, String destAirportCode, LocalDate date, Pageable pageable);
}
