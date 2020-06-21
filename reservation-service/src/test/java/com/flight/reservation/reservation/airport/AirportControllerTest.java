package com.flight.reservation.reservation.airport;

import com.flight.reservation.reservation.controller.AirportController;
import com.flight.reservation.reservation.domain.Airport;
import com.flight.reservation.reservation.mapper.AddressMapper;
import com.flight.reservation.reservation.service.AirportService;
import com.flight.reservation.reservation.service.response.AddressResponse;
import com.flight.reservation.reservation.service.response.AirportResponse;
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
@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @MockBean
    private AirportService airportService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllAirports() throws Exception {
        Airport airport = new Airport("Eastern Iowa Airport", "CID");
        AddressResponse addressResponse = AddressMapper.map(airport.getAddress());
        AirportResponse airportResponse = new AirportResponse(airport.getId(), airport.getName(), airport.getCode(), addressResponse);
        Page<AirportResponse> airportResponses = new PageImpl<>(Arrays.asList(airportResponse));

        Mockito.when(airportService.getAllAirports(PageRequest.of(0, 10))).thenReturn(airportResponses);
        mockMvc.perform(get("/airports")
                .param("pageNo", "0")
                .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(airportResponse.getName()))
                .andExpect(jsonPath("$[0].code").value(airportResponse.getCode()));
    }
}
