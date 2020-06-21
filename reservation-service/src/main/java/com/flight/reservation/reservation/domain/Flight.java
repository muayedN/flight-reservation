package com.flight.reservation.reservation.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.flight.reservation.reservation.mapper.AirlineMapper;
import com.flight.reservation.reservation.mapper.AirportMapper;
import com.flight.reservation.reservation.service.response.FlightResponse;
import com.flight.reservation.reservation.service.response.FlightResponseSerializable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
public class Flight implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int number;

	private int capacity;

	private LocalDate departureDate;

	private LocalTime departureTime;

	private LocalDate arrivalDate;

	private LocalTime arrivalTime;
	
	@ManyToOne
	@JsonManagedReference
	private Airline airline;
	
	@ManyToOne
	@JsonManagedReference
	private Airport departureAirport;
	
	@ManyToOne
	@JsonManagedReference
	private Airport arrivalAirport;
	
	@OneToMany(mappedBy = "flight")
	@JsonManagedReference
	private List<Ticket> tickets;

	public Flight() {}

	public Flight(int number, int capacity) {
		this.number = number;
		this.capacity = capacity;
	}

	public Flight(int number,
				  int capacity,
				  LocalDate departureDate,
				  LocalTime departureTime,
				  LocalDate arrivalDate,
				  LocalTime arrivalTime,
				  Airline airline,
				  Airport departureAirport,
				  Airport arrivalAirport) {
		this.number = number;
		this.capacity = capacity;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.airline = airline;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public static FlightResponse convertToResponseObject(Flight flight) {
		if (flight == null) return null;
		return new FlightResponse(flight.getId(),
				flight.getNumber(),
				flight.getCapacity(),
				flight.getDepartureDate(),
				flight.getDepartureTime(),
				flight.getArrivalDate(),
				flight.getArrivalTime(),
				AirlineMapper.map(flight.getAirline()),
				AirportMapper.map(flight.getDepartureAirport()),
				AirportMapper.map(flight.getArrivalAirport()));
	}

	public static FlightResponseSerializable convertToSerializableObject(Flight flight){
		if (flight == null) return null;
		return new FlightResponseSerializable(flight.getId(),
				flight.getNumber(),
				flight.getCapacity(),
				Date.from(flight.getDepartureDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				flight.getDepartureTime().toString(),
				Date.from(flight.getArrivalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				flight.getArrivalTime().toString(),
				AirlineMapper.map(flight.getAirline()),
				AirportMapper.map(flight.getDepartureAirport()),
				AirportMapper.map(flight.getArrivalAirport()));
	}
}
