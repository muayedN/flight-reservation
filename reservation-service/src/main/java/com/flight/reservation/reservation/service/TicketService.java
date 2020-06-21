package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.service.response.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

        public Page<TicketResponse> getAllTickets(Pageable pageable);

        public TicketResponse save(Ticket ticket);

        public TicketResponse findOne(int id);

        public void delete(int id);

        public TicketResponse update(Ticket ticket, int id);
}
