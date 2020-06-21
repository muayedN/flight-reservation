package com.flight.reservation.email.service;

import com.flight.reservation.email.domain.EmailQueue;

import java.util.List;

public interface EmailQueueService {
    List<EmailQueue> getPendingEmailDue();
    void save(EmailQueue emailQueue);
}
