package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.domain.Reservation;
import com.flight.reservation.reservation.service.response.ReservationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

	public Page<ReservationResponse> getAllReservations(Pageable pageable);

	public ReservationResponse save(Reservation reservation);

	public ReservationResponse findOne(int id);

	public void delete(int id);
	
	public ReservationResponse makeAReservation(List<Integer> flightIds, UUID passengerPublicId);
	
	public String generateRandomReservationCode();
	
	public void cancelAReservation(int id);
	
	public ReservationResponse confirmReservation(int id);

}
	


