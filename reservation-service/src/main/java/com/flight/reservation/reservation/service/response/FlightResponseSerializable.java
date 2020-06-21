package com.flight.reservation.reservation.service.response;

import java.io.Serializable;
import java.util.Date;


public class FlightResponseSerializable implements Serializable {
    private int id;

    private int number;

    private int capacity;

    private Date departureDate;

    private String departureTime;

    private Date arrivalDate;

    private String arrivalTime;

    private AirlineResponse airline;

    private AirportResponse departureAirport;

    private AirportResponse arrivalAirport;

    public FlightResponseSerializable(){}

    public FlightResponseSerializable(int id, int number, int capacity, Date departureDate, String departureTime,
                                      Date arrivalDate, String arrivalTime, AirlineResponse airline,
                                      AirportResponse departureAirport, AirportResponse arrivalAirport) {
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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

    public void setArrivalAirport(AirportResponse arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
