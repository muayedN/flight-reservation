package com.flight.reservation.reservation.service.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightRequest {

    private int id;

    private int number;

    private int capacity;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private LocalDate arrivalDate;

    private LocalTime arrivalTime;

    private AirlineRequest airlineRequest;

    private AirportRequest departureAirport;

    private AirportRequest arrivalAirport;

    public FlightRequest() {}

    public FlightRequest(int id, int number, int capacity, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, AirlineRequest airlineRequest, AirportRequest departureAirport, AirportRequest arrivalAirport) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.airlineRequest = airlineRequest;
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

    public AirlineRequest getAirlineRequest() {
        return airlineRequest;
    }

    public void setAirlineRequest(AirlineRequest airlineRequest) {
        this.airlineRequest = airlineRequest;
    }

    public AirportRequest getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportRequest departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportRequest getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportRequest arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
