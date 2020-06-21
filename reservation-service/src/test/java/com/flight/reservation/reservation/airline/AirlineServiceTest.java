package com.flight.reservation.reservation.airline;

import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.mapper.AirlineMapper;
import com.flight.reservation.reservation.repo.AirlineRepo;
import com.flight.reservation.reservation.service.AirlineService;
import com.flight.reservation.reservation.service.impl.AirlineServiceImpl;
import com.flight.reservation.reservation.service.response.AirlineResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
public class AirlineServiceTest {
    @TestConfiguration
    static class AirlineServiceTestConfig {
        @Bean
        AirlineService testableAirlineService(AirlineRepo airlineRepo) {
            return new AirlineServiceImpl(airlineRepo);
        }
    }

    @MockBean
    private AirlineRepo airlineRepo;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private AirlineService airlineService;



    @Test
    public void testGetAirlineByDepartureAirport() {

        Airline airline = new Airline("Vietnam Airlines", "VA");
        AirlineResponse airlineResponse = AirlineMapper.map(airline);
        Page<AirlineResponse> airlineResponses = new PageImpl<AirlineResponse>(Arrays.asList(airlineResponse));

        Mockito.when(airlineService.getAirlineByDepartureAirport("CID", PageRequest.of(0, 10))).thenReturn(airlineResponses);

        assertEquals(airlineService.getAirlineByDepartureAirport("CID", PageRequest.of(0, 10)), airlineResponses);

    }
}
