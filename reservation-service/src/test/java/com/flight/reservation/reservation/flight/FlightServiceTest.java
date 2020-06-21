package com.flight.reservation.reservation.flight;

import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.repo.AirportRepo;
import com.flight.reservation.reservation.repo.FlightRepo;
import com.flight.reservation.reservation.service.impl.FlightServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
public class FlightServiceTest {
    @MockBean
    private FlightServiceImpl flightService;

    @MockBean
    private FlightRepo flightRepo;

    @MockBean
    private AirportRepo airportRepo;

    private Airport laxAirport() {
        return new Airport("Los Angeles", "LAX");
    }

    private Airport nyAirport() {
        return new Airport("New York", "JFK");
    }

    private Flight flight1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Flight f = new Flight();
        f.setNumber(1);
        f.setDepartureDate(LocalDate.parse("12/05/2020", formatter));
        f.setDepartureAirport(laxAirport());
        f.setArrivalAirport(nyAirport());
        return f;
    }

    @Test
    public void testGetFlightsFilterByAirports() {
//        Flight f1 = flight1();
//        f1.setId(1);
//        String departCode = f1.getDepartureAirport().getCode();
//        String arrCode = f1.getArrivalAirport().getCode();
//        String date = f1.getDepartureDate().toString();
//        List<Flight> flights = new ArrayList<>();
//        flights.add(f1);
//        Mockito.when(flightRepo.getFlights(departCode, arrCode, f1.getDepartureDate())).thenReturn(flights);
//        assertEquals(flightService.getFlights(departCode, arrCode, date), flights);
    }
}
