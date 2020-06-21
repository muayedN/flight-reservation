package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.service.response.FlightResponse;
import com.flight.reservation.reservation.service.response.TicketResponse;

public class TicketMapper {

    public static TicketResponse map(Ticket ticket) {
        if (ticket == null) return null;
        FlightResponse flightResponse = FlightMapper.map(ticket.getFlight());
        return new TicketResponse(ticket.getId(), ticket.getNumber(), ticket.getReservationCode(), flightResponse, ticket.getUserPublicId(), ticket.getTicketStatus());
    }
}
