package com.flight.reservation.reservation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Entity
public class Ticket implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	
	
	@Column(length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE)
//	@Pattern(message = "Invalid number", regexp = "^[0-9]{20}$")
	private BigInteger number;
	@Column(length = 6)
	@Pattern(message = "Invalid reservationCode", regexp = "^[a-zA-Z0-9]{6}$")
	private String reservationCode;
	@ManyToOne
	@JsonBackReference
	private Flight flight;

	@Type(type="uuid-char")
	private UUID userPublicId;

	@Enumerated(EnumType.STRING)
	ReservationStatus ticketStatus;
	
	
	
	public ReservationStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(ReservationStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Ticket() {
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public UUID getUserPublicId() {
		return userPublicId;
	}

	public void setUserPublicId(UUID user) {
		this.userPublicId = user;
	}
}
