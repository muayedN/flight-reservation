package com.flight.reservation.reservation.service.request;

import java.util.List;

public class AirlineRequest {

    private int id;

    private String name;

    private String code;

    private String history;

    private List<FlightRequest> flightRequests;

    public AirlineRequest() {
    }

    public AirlineRequest(String name, String code, String history) {
        this.name = name;
        this.code = code;
        this.history = history;
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

    public List<FlightRequest> getFlightRequests() {
        return flightRequests;
    }

    public void setFlightRequests(List<FlightRequest> flightRequests) {
        this.flightRequests = flightRequests;
    }
}
