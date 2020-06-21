package com.flight.reservation.reservation.airline;

import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.repo.AirlineRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirlineRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AirlineRepo airlineRepo;

    private final Airline testAirline1 = new Airline("Vietnam Airlines", "VA");
    private final Airline testAirline2 = new Airline("Hongkong Airlines", "HA");
    private final Airport testAirport1 = new Airport("Eastern Iowa Airport", "CID");
    private final Airport testAirport2 = new Airport("Chicago O'Hare International Airport", "ORD");
    private final Flight testFlight = new Flight(999, 100);

    @Before
    public void setUp() {
        testEntityManager.persist(testAirline1);
        testEntityManager.persist(testAirline2);
        testEntityManager.persist(testAirport1);
        testEntityManager.persist(testAirport2);

        testFlight.setAirline(testAirline1);
        testFlight.setDepartureAirport(testAirport1);
        testFlight.setArrivalAirport(testAirport2);
        testEntityManager.persist(testFlight);

        testEntityManager.flush();

    }

    @After
    public void cleanUp() {
        testEntityManager.remove(testAirline1);
        testEntityManager.remove(testAirline2);
        testEntityManager.remove(testAirport1);
        testEntityManager.remove(testAirport2);
        testEntityManager.remove(testFlight);

        testEntityManager.flush();

    }

    @Test
    public void testGetAirlineByDepartureAirport() {

        Page<Airline> airlines = airlineRepo.getAirlineByDepartureAirport(testAirline1.getCode(), PageRequest.of(0, 10));

        assertThat(airlines, hasItem(hasProperty("name", is(testAirline1.getName()))));
        assertThat(airlines, hasItem(hasProperty("code", is(testAirline1.getCode()))));

    }
}
