package com.flight.reservation.email.repository;

import com.flight.reservation.email.domain.EmailQueue;
import com.flight.reservation.email.domain.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmailQueueRepository extends JpaRepository<EmailQueue, Integer> {
    List<EmailQueue> findAllByEmailStatusAndEarliestFlightDateBetween(EmailStatus emailStatus, Date startDate, Date endDate);
}
