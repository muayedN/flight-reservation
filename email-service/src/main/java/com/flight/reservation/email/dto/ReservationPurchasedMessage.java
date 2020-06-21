package com.flight.reservation.email.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ReservationPurchasedMessage implements Serializable {
    private List<FlightResponseSerializable> flights;
    private UUID userPublicId;
    private String email;

    public ReservationPurchasedMessage(){}

    public ReservationPurchasedMessage(UUID userPublicId, List<FlightResponseSerializable> flights) {
        this.flights = flights;
        this.userPublicId = userPublicId;
    }

    public List<FlightResponseSerializable> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightResponseSerializable> flights) {
        this.flights = flights;
    }

    public UUID getUserPublicId() {
        return userPublicId;
    }

    public void setUserPublicId(UUID userPublicId) {
        this.userPublicId = userPublicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
