package com.flight.reservation.reservation.service.response;

import java.io.Serializable;

public interface JmsService {
    void publishMessageToBroker(ReservationPurchasedMessage message);
}
