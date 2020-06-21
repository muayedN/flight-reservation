package com.flight.reservation.reservation.repo;

import java.util.Optional;
import java.util.UUID;

import com.flight.reservation.reservation.service.response.ReservationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flight.reservation.reservation.domain.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{
	
	public boolean existsByCode(String code);

	Page<Reservation> findAllByCreatedByUserPublicIdOrCreatedForUserPublicId(Pageable pageable,
																			 UUID createdByUserPublicId,
																			 UUID createdForUserPublicId);

	@Query("Select r FROM Reservation r join fetch r.flights WHERE r.id = :id")
	public Optional<Reservation> findOneEagerLoadFlights(int id);

}
