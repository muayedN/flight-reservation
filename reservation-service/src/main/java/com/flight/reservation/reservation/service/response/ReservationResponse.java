package com.flight.reservation.reservation.service.response;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.domain.ReservationStatus;
import com.flight.reservation.reservation.domain.Ticket;

public class ReservationResponse implements Serializable {

	private int id;

	private UUID createdBy;

	private UUID createdFor;

	private ReservationStatus reservationStatus;

	private String code;

	private List<TicketResponse> ticketsPerReservation;

	private List<FlightResponse> flights;
	
	
	public ReservationResponse() {
		
	}
	

	public ReservationResponse(int id, UUID createdBy, UUID createdFor, ReservationStatus reservationStatus,
			String code, List<TicketResponse> ticketsPerReservation, List<FlightResponse> flights) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdFor = createdFor;
		this.reservationStatus = reservationStatus;
		this.code = code;
		this.ticketsPerReservation = ticketsPerReservation;
		this.flights = flights;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public List<FlightResponse> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightResponse> flights) {
		this.flights = flights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public UUID getCreatedFor() {
		return createdFor;
	}

	public void setCreatedFor(UUID createdFor) {
		this.createdFor = createdFor;
	}

	public ReservationStatus getStatus() {
		return reservationStatus;
	}

	public void setStatus(ReservationStatus status) {
		this.reservationStatus = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TicketResponse> getTicketsPerReservation() {
		return ticketsPerReservation;
	}

	public void setTicketsPerReservation(List<TicketResponse> ticketsPerReservation) {
		this.ticketsPerReservation = ticketsPerReservation;
	}
}
