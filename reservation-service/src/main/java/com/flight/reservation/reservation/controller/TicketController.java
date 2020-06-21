package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.service.TicketService;
import com.flight.reservation.reservation.service.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TicketController {

    private final TicketService ticketService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TicketController (TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Page<TicketResponse> getAllTickets(Pageable pageable) {
        Page<TicketResponse> tickets = ticketService.getAllTickets(pageable);
        return tickets;
    }

    @PostMapping
    public TicketResponse addTicket(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping("/{id}")
    public TicketResponse getTicket(@PathVariable int id) {
        return ticketService.findOne(id);
    }

    @PutMapping("/{id}")
    public TicketResponse updateTicket(@RequestBody Ticket ticket, @PathVariable int id) {
        return ticketService.update(ticket, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable int id) {
        ticketService.delete(id);
    }
}
