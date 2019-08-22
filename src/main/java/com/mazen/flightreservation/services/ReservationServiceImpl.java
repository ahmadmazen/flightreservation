package com.mazen.flightreservation.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mazen.flightreservation.dto.ReservationRequest;
import com.mazen.flightreservation.entities.Flight;
import com.mazen.flightreservation.entities.Passenger;
import com.mazen.flightreservation.entities.Reservation;
import com.mazen.flightreservation.repos.FlightRepository;
import com.mazen.flightreservation.repos.PassengerRepository;
import com.mazen.flightreservation.repos.ReservationRepository;
import com.mazen.flightreservation.util.EmailUtil;
import com.mazen.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.mazen.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	EmailUtil emailUtil;
	@Autowired
	PDFGenerator pdfGenerator;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		// Make payment.. if succeeded continue with adding booking information
		
		LOGGER.info("inside bookFlight()");


		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findOne(flightId);
		LOGGER.info("Fetching Flight for flight id : " + flightId);
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		Passenger savedPassenger = passengerRepository.save(passenger);
		LOGGER.info("Saving the passenger : " + passenger);

		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		LOGGER.info("Saving the reservation : " + savedReservation);


		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Generating the Itinerary");

		emailUtil.sendItinerary(savedPassenger.getEmail(), filePath);

		LOGGER.info("Emailing the Itinerary");

		return savedReservation;
	}

}
