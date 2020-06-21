package com.flight.reservation.reservation.reservation;

import com.flight.reservation.reservation.domain.Reservation;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.domain.ReservationStatus;
import com.flight.reservation.reservation.repo.ReservationRepo;
import com.flight.reservation.reservation.service.response.ReservationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class ReservationRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationRepo reservationRepo;

    private final Reservation testReservation1 = new Reservation(ReservationStatus.CONFIRMED,"VA");

    @Before
    public void setUp() {
        testEntityManager.persist(testReservation1);

        testEntityManager.persist(testReservation1);

        testEntityManager.flush();

    }

    @After
    public void cleanUp() {
        testEntityManager.remove(testReservation1);
        testEntityManager.flush();

    }

    @Test
    public void testGetReservationByDepartureAirport() {

        Page<Reservation> reservations =  reservationRepo.findAllByCreatedByUserPublicIdOrCreatedForUserPublicId(PageRequest.of(0, 10), UUID.randomUUID(), UUID.randomUUID());

        assertThat(reservations, hasItem(hasProperty("name", is(testReservation1.getReservationStatus()))));

    }
}
