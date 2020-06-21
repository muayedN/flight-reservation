package com.flight.reservation.reservation.airline;

import com.flight.reservation.reservation.controller.AirlineController;
import com.flight.reservation.reservation.domain.Airline;
import com.flight.reservation.reservation.service.AirlineService;
import com.flight.reservation.reservation.service.response.AirlineResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AirlineController.class)
public class AirlineControllerTest {

    @MockBean
    private AirlineService airlineService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAirlineByDepartureAirport() throws Exception {
        Airline airline = new Airline("Vietnam Airlines", "VA");
        AirlineResponse airlineResponse = new AirlineResponse(airline.getId(), airline.getName(), airline.getCode(), airline.getHistory());
        Page<AirlineResponse> airlineResponses = new PageImpl<AirlineResponse>(Arrays.asList(airlineResponse));

        Mockito.when(airlineService.getAirlineByDepartureAirport("CID", PageRequest.of(0, 10))).thenReturn(airlineResponses);
        mockMvc.perform(get("/airlines/departureAirport?code=CID")
                .param("pageNo", "0")
                .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(airlineResponse.getName()))
                .andExpect(jsonPath("$[0].code").value(airlineResponse.getCode()));
    }
}

