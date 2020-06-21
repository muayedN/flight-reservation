package com.flight.reservation.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flight.reservation.email.dto.ReservationPurchasedMessage;
import com.flight.reservation.email.mapper.EmailQueueMapper;
import com.flight.reservation.email.service.EmailQueueService;
import com.flight.reservation.email.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationListener {

    private final EmailQueueService emailQueueService;

    private final EmailQueueMapper emailQueueMapper;

    private final UserService userService;

    @Autowired
    public ReservationListener(EmailQueueService emailQueueService,
                               EmailQueueMapper emailQueueMapper,
                               UserService userService) {

        this.emailQueueService = emailQueueService;
        this.emailQueueMapper = emailQueueMapper;
        this.userService = userService;

    }

    //TODO: read string from config file
    @JmsListener(destination = "flight-reservation")
    public void consumeReservationPurchaseMessage(String reservationPurchasedMessageStr) {
        ReservationPurchasedMessage reservationPurchasedMessage = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            reservationPurchasedMessage = mapper.readValue(reservationPurchasedMessageStr, ReservationPurchasedMessage.class);
            reservationPurchasedMessage.setEmail(userService.getUserWithPublicId(reservationPurchasedMessage.getUserPublicId()).getEmail());
            emailQueueService.save(emailQueueMapper.map(reservationPurchasedMessage));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
