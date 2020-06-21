package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Flight;
import com.flight.reservation.reservation.domain.Reservation;
import com.flight.reservation.reservation.domain.ReservationStatus;
import com.flight.reservation.reservation.domain.Ticket;
import com.flight.reservation.reservation.exceptions.InvalidFlightException;
import com.flight.reservation.reservation.exceptions.PurchasingReservationWithAnInvalidState;
import com.flight.reservation.reservation.exceptions.ReservationNotFoundException;
import com.flight.reservation.reservation.exceptions.UnAuthorizedReservationAccessException;
import com.flight.reservation.reservation.mapper.FlightMapper;
import com.flight.reservation.reservation.mapper.ReservationMapper;
import com.flight.reservation.reservation.repo.FlightRepo;
import com.flight.reservation.reservation.repo.ReservationRepo;
import com.flight.reservation.reservation.repo.TicketRepo;
import com.flight.reservation.reservation.service.AccountService;
import com.flight.reservation.reservation.service.ReservationService;
import com.flight.reservation.reservation.service.response.JmsService;
import com.flight.reservation.reservation.service.response.ReservationPurchasedMessage;
import com.flight.reservation.reservation.service.response.ReservationResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class ReservationServiceImpl implements ReservationService {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ReservationRepo reservationRepo;

	@Autowired
	private FlightRepo flightRepo;

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private AccountService accountService;

	private JmsService jmsService;

	@Override
	public Page<ReservationResponse> getAllReservations(Pageable pageable) {
		List<ReservationResponse> reservationResponses = reservationRepo.findAll().stream().parallel()
				.map(ReservationMapper::map)
				.collect(Collectors.toList());
		return new PageImpl<>(reservationResponses);
	}

	public ReservationResponse save(Reservation reservation) {

		return ReservationMapper.map(reservationRepo.save(reservation));

	}

	public ReservationResponse findOne(int id) {
		return ReservationMapper.map(reservationRepo.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation Not Found!")));
	}

	public void delete(int id) {

		reservationRepo.deleteById(id);
	}

	@Transactional
	public ReservationResponse makeAReservation(List<Integer> flightIds, UUID passengerPublicId) throws InvalidFlightException {

		Reservation reservation = new Reservation();

		List<Flight> flights = flightRepo.findAllById(flightIds);

		if (flightIds.size() == flights.size() && flights.size() > 0) {
			reservation.setFlights(flights);
		} else
			throw new InvalidFlightException("Incorrect flight Ids ");

		reservation.setCreatedByUserPublicId(accountService.getLoggedInUserPublicId());
		reservation.setCreatedForUserPublicId(passengerPublicId);
		reservation.setReservationStatus(ReservationStatus.PENDING);

		String uuid = generateRandomReservationCode();
		reservation.setCode(uuid);

		reservationRepo.save(reservation);

		// map to reservation response
		return ReservationMapper.map(reservation);
	}

	@Transactional
	public void cancelAReservation(int id) {

		Reservation reservation = reservationRepo.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation NOT FOUND!"));

		if (!isLoggedInUserAuthorizedToModifyReservation(reservation))
			throw new UnAuthorizedReservationAccessException();

		if (!reservation.getReservationStatus().equals(ReservationStatus.PENDING)) {
			throw new PurchasingReservationWithAnInvalidState(reservation.getReservationStatus());
		}

		reservation.setReservationStatus(ReservationStatus.CANCELED);
		List<Ticket> tickets = reservation.getTicketsPerReservation();

		for (Ticket t : tickets) {
			t.setTicketStatus(ReservationStatus.CANCELED);
		}
		reservationRepo.save(reservation);
		ticketRepo.saveAll(tickets);

	}

	@Transactional
	public ReservationResponse confirmReservation(int id) {
		Reservation reservation = reservationRepo.findOneEagerLoadFlights(id)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation NOT FOUND!"));

		if (!isLoggedInUserAuthorizedToModifyReservation(reservation))
			throw new UnAuthorizedReservationAccessException();

		if (!reservation.getReservationStatus().equals(ReservationStatus.PENDING)) {
			throw new PurchasingReservationWithAnInvalidState(reservation.getReservationStatus());
		}

		reservation.setReservationStatus(ReservationStatus.CONFIRMED);

		List<Flight> flights = reservation.getFlights();
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (Flight f : flights) {
			Ticket t = new Ticket();
			t.setFlight(f);
			t.setReservationCode(reservation.getCode());
			t.setTicketStatus(ReservationStatus.CONFIRMED);
			t.setUserPublicId(reservation.getCreatedForUserPublicId());
			tickets.add(t);

		}

		reservation.setTicketsPerReservation(tickets);
		ticketRepo.saveAll(tickets);
		reservationRepo.save(reservation);

		jmsService.publishMessageToBroker(createReservationPurchaseMessage(reservation));

		return ReservationMapper.map(reservation);
	}

	public String generateRandomReservationCode() {
		String uuid = UUID.randomUUID().toString().substring(0, 6);
		while (reservationRepo.existsByCode(uuid)) {
			uuid = UUID.randomUUID().toString().substring(0, 6);
			logger.warn("Reservation Code Already Exists");

		}
		return uuid;
	}

	private ReservationPurchasedMessage createReservationPurchaseMessage(Reservation reservation){
		return new ReservationPurchasedMessage(
				reservation.getCreatedForUserPublicId(),
				reservation.getFlights().stream().map(FlightMapper::toSerializableObject).collect(Collectors.toList()));
	}

	private boolean isLoggedInUserAuthorizedToModifyReservation(Reservation reservation){
		return accountService.getLoggedInUserPublicId().equals(reservation.getCreatedByUserPublicId());
	}
}
