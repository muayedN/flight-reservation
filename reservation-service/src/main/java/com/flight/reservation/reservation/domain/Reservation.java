package com.flight.reservation.reservation.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

@Entity
public class Reservation implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;

	@Type(type="uuid-char")
	private UUID createdByUserPublicId;

	@Type(type="uuid-char")
	private UUID createdForUserPublicId;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@Column(length = 6, unique = true)
	@Pattern(regexp ="^[a-zA-Z0-9]{6}$")
	private String code;

	@JsonBackReference
	@OneToMany()
	private List<Ticket> ticketsPerReservation = new ArrayList<>();

	@JsonBackReference
	@ManyToMany
	private List<Flight> flights = new ArrayList<>();

	public Reservation() {}
	public Reservation(ReservationStatus reservationStatus,String code) {
		this.reservationStatus=reservationStatus;
		this.code=code;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UUID getCreatedByUserPublicId() {
		return createdByUserPublicId;
	}

	public void setCreatedByUserPublicId(UUID createdBy) {
		this.createdByUserPublicId = createdBy;
	}

	public UUID getCreatedForUserPublicId() {
		return createdForUserPublicId;
	}

	public void setCreatedForUserPublicId(UUID createdFor) {
		this.createdForUserPublicId = createdFor;
	}

	public void setReservationStatus(ReservationStatus status) {
		this.reservationStatus = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Ticket> getTicketsPerReservation() {
		return ticketsPerReservation;
	}

	public void setTicketsPerReservation(List<Ticket> ticketsPerReservation) {
		this.ticketsPerReservation = ticketsPerReservation;
	}
	
	

}
