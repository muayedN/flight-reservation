package com.flight.reservation.email.service.impl;

import com.flight.reservation.email.domain.EmailQueue;
import com.flight.reservation.email.domain.EmailStatus;
import com.flight.reservation.email.repository.EmailQueueRepository;
import com.flight.reservation.email.service.EmailQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmailQueueServiceImpl implements EmailQueueService {

    private final EmailQueueRepository emailQueueRepository;

    public final JavaMailSender emailSender;

    public static final String FLIGHT_RESERVATION_REMINDER_SUBJECT = "Flight reservation reminder";

    @Autowired
    public EmailQueueServiceImpl(EmailQueueRepository emailQueueRepository, JavaMailSender emailSender) {
        this.emailQueueRepository = emailQueueRepository;
        this.emailSender = emailSender;
    }

    @Override
    public List<EmailQueue> getPendingEmailDue() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 1);
        Date tmw = c.getTime();
        return emailQueueRepository.findAllByEmailStatusAndEarliestFlightDateBetween(EmailStatus.PENDING, now, tmw);
    }

    @Override
    public void save(EmailQueue emailQueue) {
        emailQueue.setEmailStatus(EmailStatus.PENDING);
        emailQueueRepository.save(emailQueue);
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void sendFlightReservationReminder() {
        List<EmailQueue> emailQueueToSend = getPendingEmailDue();
        for (EmailQueue emailQueue : emailQueueToSend) {
            sendEmail(emailQueue);
            emailQueue.setEmailStatus(EmailStatus.SENT);
            emailQueueRepository.saveAndFlush(emailQueue);
        }
    }

    private void sendEmail(EmailQueue emailQueue){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailQueue.getEmail());
        message.setSubject(FLIGHT_RESERVATION_REMINDER_SUBJECT);
        //TODO include reservation and flights
        message.setText("You have a flight coming up tomorrow");
        emailSender.send(message);
    }
}
