package com.flight.reservation.reservation.repo;

import com.flight.reservation.reservation.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer>{

}
