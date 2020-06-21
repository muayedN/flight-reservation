package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.service.AccountService;
import com.flight.reservation.reservation.service.ReservationService;
import com.flight.reservation.reservation.service.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	AccountService accountService;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<ReservationResponse> getAllReservations(Pageable pageable) {
		return reservationService.getAllReservations(pageable);
	}

	// making the reservation will have a status of pending until it is
	// confirmed/purchased.
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
	public ReservationResponse makeAReservation(@RequestBody List<Integer> flightIds) {

		return reservationService.makeAReservation(flightIds,  accountService.getLoggedInUserPublicId());
	}

	@PostMapping("/{passengerPublicId}")
	@PreAuthorize("hasAuthority('ROLE_AGENT')")
	public ReservationResponse makeReservationForPassenger(@RequestBody List<Integer> flightIds,
														   @PathVariable String passengerPublicId) {
		return reservationService.makeAReservation(flightIds, UUID.fromString(passengerPublicId));
	}

	@PutMapping("/{id}/cancel")
	@PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> cancelAReservation(@PathVariable("id") int id) {

		reservationService.cancelAReservation(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}/purchase")
	@PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> purchaseAReservation(@PathVariable("id") int id) {

		reservationService.confirmReservation(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
