package com.flight.reservation.reservation.service.response;

import com.flight.reservation.reservation.domain.ReservationStatus;

import java.math.BigInteger;
import java.util.UUID;

public class TicketResponse {
	
	private int id;

	private BigInteger number;
	
	private String reservationCode;

	private FlightResponse flightResponse;
	
	private UUID user;

	ReservationStatus ticketStatus;
	
	public TicketResponse(int id, BigInteger number, String reservationCode, FlightResponse flightResponse, UUID user,
			ReservationStatus ticketStatus) {
		super();
		this.id = id;
		this.number = number;
		this.reservationCode = reservationCode;
		this.flightResponse = flightResponse;
		this.user = user;
		this.ticketStatus = ticketStatus;
	}

	public ReservationStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(ReservationStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public TicketResponse() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getNumber() {
		return number;
	}

	public void setNumber(BigInteger number) {
		this.number = number;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationDate) {
		this.reservationCode = reservationDate;
	}

	public FlightResponse getFlightResponse() {
		return flightResponse;
	}

	public void setFlightResponse(FlightResponse flightResponse) {
		this.flightResponse = flightResponse;
	}

	public UUID getUser() {
		return user;
	}

	public void setUser(UUID user) {
		this.user = user;
	}

}
