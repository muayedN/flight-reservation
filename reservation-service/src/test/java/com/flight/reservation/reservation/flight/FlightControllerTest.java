package com.flight.reservation.reservation.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.reservation.reservation.controller.FlightController;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.mapper.FlightMapper;
import com.flight.reservation.reservation.service.FlightService;
import com.flight.reservation.reservation.service.response.FlightResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private Airport laxAirport() {
        return new Airport("Los Angeles", "LAX");
    }

    private Airport nyAirport() {
        return new Airport("New York", "JFK");
    }

    private Airport ordAirport() {
        return new Airport("Chicago", "ORD");
    }

    private Flight flight1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Flight f = new Flight();
        f.setId(1);
        f.setNumber(1);
        f.setDepartureDate(LocalDate.parse("12/05/2020", formatter));
        f.setDepartureAirport(laxAirport());
        f.setArrivalAirport(nyAirport());
        return f;
    }

    private Flight flight2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Flight f = new Flight();
        f.setId(2);
        f.setNumber(2);
        f.setDepartureDate(LocalDate.parse("17/05/2020", formatter));
        f.setDepartureAirport(ordAirport());
        f.setArrivalAirport(nyAirport());
        return f;
    }

    @Test
    void getAllFlights() throws Exception {
        FlightResponse f1 = FlightMapper.map(flight1());
        FlightResponse f2 = FlightMapper.map(flight2());
        Page<FlightResponse> flights = new PageImpl<>(Arrays.asList(f1, f2));
        Mockito.when(flightService.findAll(PageRequest.of(0, 10))).thenReturn(flights);
        mockMvc.perform(get("/flights")
                .param("pageNo", "0")
                .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number").value(f1.getNumber()))
                .andExpect(jsonPath("$[1].number").value(f2.getNumber()));
    }

    @Test
    void getFlightsByAirports() throws Exception {
        Flight f1 = flight1();
        String departCode = f1.getDepartureAirport().getCode();
        String arrCode = f1.getArrivalAirport().getCode();
        LocalDate date = f1.getDepartureDate();
        List<FlightResponse> flights = new ArrayList<>();
        flights.add(FlightMapper.map(f1));

        Page<FlightResponse> flightPages = new PageImpl<>(flights);
        Mockito.when(flightService.getFlights(departCode, arrCode, date, PageRequest.of(0, 10)))
                .thenReturn(flightPages);
        mockMvc.perform(get("/flights")
                .param("departAirportCode", departCode)
                .param("destAirportCode", arrCode)
                .param("date", "2020-05-12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number").value(f1.getNumber()));
    }

    @Test
    void updateFlight() throws Exception {
        Flight f1 = flight1();
        FlightResponse f1Response = FlightMapper.map(f1);
        Mockito.when(flightService.save(f1)).thenReturn(f1Response);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/flights/1")
                .content(asJsonString(f1Response))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number").value(f1.getNumber()));
    }

    @Test
    void createNewFlight() throws Exception {
        Flight f1 = flight1();
        FlightResponse f1Response = FlightMapper.map(f1);
        Mockito.when(flightService.save(f1)).thenReturn(f1Response);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/flights")
                .content(asJsonString(f1Response))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number").value(f1.getNumber()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
