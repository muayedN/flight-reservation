package com.flight.reservation.reservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@SecondaryTable(name = "history")
public class Airline implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(length = 3)
    private String code;

    @Column(table = "history", length = 2000)
    private String history;
    
    @OneToMany(mappedBy = "airline")
	@JsonIgnore
    private List<Flight> flights;

	public Airline() {
	}

	public Airline(String name, String code, String history) {
		this.name = name;
		this.code = code;
		this.history = history;
	}

	public Airline(String name, String code) {
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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
    
}
