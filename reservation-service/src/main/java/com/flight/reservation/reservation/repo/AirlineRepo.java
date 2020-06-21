package com.flight.reservation.reservation.repo;

import com.flight.reservation.reservation.domain.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, Integer>{

    @Query(value = "SELECT distinct f.airline FROM Flight f WHERE f.departureAirport.code = (:code)", nativeQuery = false)
    Page<Airline> getAirlineByDepartureAirport(String code, Pageable pageable);

}
