package com.flight.reservation.reservation;

import com.flight.reservation.reservation.controller.AirlineController;
import com.flight.reservation.reservation.controller.AirportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationApplicationTests {

	@Autowired
	private AirportController airportController;

	@Autowired
	private AirlineController airlineController;

	@Test
	void contextLoads() {
		assertThat(airportController).isNotNull();
		assertThat(airlineController).isNotNull();
	}

}
