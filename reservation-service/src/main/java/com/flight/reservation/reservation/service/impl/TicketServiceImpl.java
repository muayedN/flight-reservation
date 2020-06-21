package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.exceptions.NotFoundException;
import com.flight.reservation.reservation.exceptions.SavingFailureException;
import com.flight.reservation.reservation.mapper.TicketMapper;
import com.flight.reservation.reservation.repo.TicketRepo;
import com.flight.reservation.reservation.service.TicketService;
import com.flight.reservation.reservation.service.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public Page<TicketResponse> getAllTickets(Pageable pageable) {

        List<TicketResponse> ticketResponses = ticketRepo.findAll().stream().parallel()
                .map(TicketMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(ticketResponses);
    }

    @Override
    public TicketResponse save(Ticket ticket) {

        TicketResponse ticketResponse = TicketMapper.map(ticketRepo.save(ticket));
        if (ticketResponse == null) {
            throw new SavingFailureException("Saving failed");
        }
        return ticketResponse;
    }

    @Override
    public TicketResponse findOne(int id) {

        return TicketMapper.map(ticketRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the Ticket")));
    }

    @Override
    public void delete(int id) {

        ticketRepo.deleteById(id);
    }

    @Override
    public TicketResponse update(Ticket newTicket, int id) {

        newTicket.setId(id);
        if (save(newTicket) == null) {
            throw new SavingFailureException("Updating failed");
        }
        return save(newTicket);
    }
}

