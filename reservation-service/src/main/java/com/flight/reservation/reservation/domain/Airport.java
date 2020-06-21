package com.flight.reservation.reservation.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column(length = 3)
	private String code;

	@OneToMany(mappedBy = "arrivalAirport")
	@JsonManagedReference
	private List<Flight> arrivalFlights = new ArrayList<>();

	@OneToMany(mappedBy = "departureAirport")
	@JsonManagedReference
	private List<Flight> departureFlights = new ArrayList<>();

	@OneToOne
	private Address address;

	public Airport(){}

	public Airport(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}

	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}

	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
