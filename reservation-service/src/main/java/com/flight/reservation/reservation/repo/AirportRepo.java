package com.flight.reservation.reservation.repo;

import com.flight.reservation.reservation.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Integer>{

}
