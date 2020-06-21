package com.flight.reservation.reservation.service.request;

import com.flight.reservation.reservation.domain.ReservationStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationRequest {

    private int id;

    private UUID createdBy;

    private UUID createdFor;

    private ReservationStatus reservationStatus;

    private String code;

    private List<TicketRequest> ticketsPerReservation = new ArrayList<>();

    private List<FlightRequest> flights = new ArrayList<>();

    public ReservationRequest() {}

    public ReservationRequest(int id, UUID createdBy, UUID createdFor, ReservationStatus reservationStatus, String code, List<TicketRequest> ticketsPerReservation, List<FlightRequest> flights) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdFor = createdFor;
        this.reservationStatus = reservationStatus;
        this.code = code;
        this.ticketsPerReservation = ticketsPerReservation;
        this.flights = flights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(UUID createdFor) {
        this.createdFor = createdFor;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TicketRequest> getTicketsPerReservation() {
        return ticketsPerReservation;
    }

    public void setTicketsPerReservation(List<TicketRequest> ticketsPerReservation) {
        this.ticketsPerReservation = ticketsPerReservation;
    }

    public List<FlightRequest> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightRequest> flights) {
        this.flights = flights;
    }
}
