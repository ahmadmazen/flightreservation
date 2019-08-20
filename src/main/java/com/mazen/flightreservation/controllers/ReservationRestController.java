package com.mazen.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazen.flightreservation.dto.ReservationUpdateRequest;
import com.mazen.flightreservation.entities.Reservation;
import com.mazen.flightreservation.repos.ReservationRepository;
import com.mazen.flightreservation.util.PDFGenerator;

@RestController
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);


	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable Long id) {

		LOGGER.info("inside findReservation()");
		return reservationRepository.findOne(id);

	}

	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {

		LOGGER.info("inside updateReservation() | request is : " + request);

		Reservation reservation = reservationRepository.findOne(request.getId());
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		
		LOGGER.info("Saving reservation");
		return reservationRepository.save(reservation);

	}

}
