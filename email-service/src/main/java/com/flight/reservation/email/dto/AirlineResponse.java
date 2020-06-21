package com.flight.reservation.email.dto;

import java.io.Serializable;

public class AirlineResponse implements Serializable {

    private int id;

    private String name;

    private String code;

    private String history;

    public AirlineResponse(){}
    public AirlineResponse(int id,
                           String name,
                           String code,
                           String history) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.history = history;
    }

    public long getId() {
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
}
