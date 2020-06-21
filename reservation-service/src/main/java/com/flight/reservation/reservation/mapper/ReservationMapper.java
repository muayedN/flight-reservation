package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Reservation;
import com.flight.reservation.reservation.service.response.FlightResponse;
import com.flight.reservation.reservation.service.response.ReservationResponse;
import com.flight.reservation.reservation.service.response.TicketResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    public static ReservationResponse map(Reservation reservation) {
        List<TicketResponse> ticketResponses = reservation.getTicketsPerReservation().stream()
                .map(t -> TicketMapper.map(t)).collect(Collectors.toList());

        List<FlightResponse> flightResponses = reservation.getFlights().stream().map(f -> FlightMapper.map(f))
                .collect(Collectors.toList());

        return new ReservationResponse(reservation.getId(), reservation.getCreatedByUserPublicId(), reservation.getCreatedForUserPublicId(),
                reservation.getReservationStatus(), reservation.getCode(), ticketResponses, flightResponses);
    }
}
