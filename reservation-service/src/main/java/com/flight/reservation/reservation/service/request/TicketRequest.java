package com.flight.reservation.reservation.service.request;

import com.flight.reservation.reservation.domain.ReservationStatus;

import java.math.BigInteger;
import java.util.UUID;

public class TicketRequest {

    private int id;

    private BigInteger number;

    private String reservationCode;

    private FlightRequest flightRequest;

    private UUID userPublicId;

    ReservationStatus ticketStatus;

    public TicketRequest() {
    }

    public TicketRequest(int id, BigInteger number, String reservationCode, FlightRequest flightRequest, UUID userPublicId, ReservationStatus ticketStatus) {
        this.id = id;
        this.number = number;
        this.reservationCode = reservationCode;
        this.flightRequest = flightRequest;
        this.userPublicId = userPublicId;
        this.ticketStatus = ticketStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public FlightRequest getFlightRequest() {
        return flightRequest;
    }

    public void setFlightRequest(FlightRequest flightRequest) {
        this.flightRequest = flightRequest;
    }

    public UUID getUserPublicId() {
        return userPublicId;
    }

    public void setUserPublicId(UUID userPublicId) {
        this.userPublicId = userPublicId;
    }

    public ReservationStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(ReservationStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
