package com.flight.reservation.email.mapper;

import com.flight.reservation.email.domain.EmailQueue;
import com.flight.reservation.email.domain.EmailStatus;
import com.flight.reservation.email.dto.FlightResponseSerializable;
import com.flight.reservation.email.dto.ReservationPurchasedMessage;
import com.flight.reservation.email.exception.ReservationWithAnEmptyFlightException;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class EmailQueueMapper {

    public EmailQueue map(ReservationPurchasedMessage reservationPurchasedMessage){
        return new EmailQueue(
                reservationPurchasedMessage.getUserPublicId(),
                reservationPurchasedMessage.getFlights()
                        .stream()
                        .map(FlightResponseSerializable::getDepartureDate)
                        .max(Comparator.naturalOrder())
                        .orElseThrow(ReservationWithAnEmptyFlightException::new),
                reservationPurchasedMessage.getEmail(),
                EmailStatus.PENDING);
    }
}
