package com.flight.reservation.reservation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flight.reservation.reservation.service.response.JmsService;
import com.flight.reservation.reservation.service.response.ReservationPurchasedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.io.Serializable;

@Service
public class JmsServiceImpl implements JmsService {

    private final Queue queue;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServiceImpl(Queue queue, JmsTemplate jmsTemplate) {
        this.queue = queue;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void publishMessageToBroker(ReservationPurchasedMessage message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            jmsTemplate.convertAndSend(queue, mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
