package com.flight.reservation.reservation.service.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.repo.AirportRepo;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightResponse implements Serializable {
    private int id;

    private int number;

    private int capacity;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private LocalDate arrivalDate;

    private LocalTime arrivalTime;

    private AirlineResponse airline;

    private AirportResponse departureAirport;

    private AirportResponse arrivalAirport;

    public FlightResponse(){}
    public FlightResponse(int id,
                          int number,
                          int capacity,
                          LocalDate departureDate,
                          LocalTime departureTime,
                          LocalDate arrivalDate,
                          LocalTime arrivalTime,
                          AirlineResponse airline,
                          AirportResponse departureAirport,
                          AirportResponse arrivalAirport) {
        this.id = id;
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

    public AirlineResponse getAirline() {
        return airline;
    }

    public void setAirline(AirlineResponse airline) {
        this.airline = airline;
    }

    public AirportResponse getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportResponse departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportResponse getArrivalAirport() {
        return arrivalAirport;
    }

}
