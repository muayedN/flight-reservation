package com.flight.reservation.reservation.airport;

import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.mapper.AddressMapper;
import com.flight.reservation.reservation.repo.AirportRepo;
import com.flight.reservation.reservation.service.AirportService;
import com.flight.reservation.reservation.service.impl.AirportServiceImpl;
import com.flight.reservation.reservation.service.response.AddressResponse;
import com.flight.reservation.reservation.service.response.AirportResponse;
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
public class AirportServiceTest {
    @TestConfiguration
    static class AirportServiceTestConfig {
        @Bean
        AirportService testableAirportService(AirportRepo airportRepo) {
            return new AirportServiceImpl(airportRepo);
        }
    }

    @MockBean
    private AirportRepo airportRepo;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private AirportService airportService;

    @Test
    public void testGetAllAirports() {

        Airport airport1 = new Airport("Eastern Iowa Airport", "CID");
        Airport airport2 = new Airport("Chicago O'Hare International Airport", "ORD");
        AddressResponse addressResponse1 = AddressMapper.map(airport1.getAddress());
        AddressResponse addressResponse2 = AddressMapper.map(airport2.getAddress());
        AirportResponse airportResponse1 = new AirportResponse(airport1.getId(), airport1.getName(), airport1.getCode(), addressResponse1);
        AirportResponse airportResponse2 = new AirportResponse(airport2.getId(), airport2.getName(), airport2.getCode(), addressResponse2);
        Page<AirportResponse> airportResponses = new PageImpl<AirportResponse>(Arrays.asList(airportResponse1, airportResponse2));

        Mockito.when(airportService.getAllAirports(PageRequest.of(0, 10))).thenReturn(airportResponses);

        assertEquals(airportService.getAllAirports(PageRequest.of(0, 10)), airportResponses);
    }
}
