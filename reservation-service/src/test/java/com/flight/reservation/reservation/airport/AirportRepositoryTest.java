package com.flight.reservation.reservation.airport;

import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.repo.AirportRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirportRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AirportRepo airportRepo;

    private final Airport testAirport1 = new Airport("Eastern Iowa Airport", "CID");
    private final Airport testAirport2 = new Airport("Chicago O'Hare International Airport", "ORD");

    @Before
    public void setUp(){

        testEntityManager.persist(testAirport1);
        testEntityManager.persist(testAirport2);

        testEntityManager.flush();

    }

    @After
    public void cleanUp(){

        testEntityManager.remove(testAirport1);
        testEntityManager.remove(testAirport2);

        testEntityManager.flush();

    }

    @Test
    public void testFindAllReturnsAllCountries(){

        List<Airport> airports = airportRepo.findAll();

        assertThat(airports, hasItem(hasProperty("name", is(testAirport1.getName()))));
        assertThat(airports, hasItem(hasProperty("name", is(testAirport2.getName()))));

        assertThat(airports, hasItem(hasProperty("code", is(testAirport1.getCode()))));
        assertThat(airports, hasItem(hasProperty("code", is(testAirport2.getCode()))));

    }

}
