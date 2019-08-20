package com.mazen.flightreservation.services;

import com.mazen.flightreservation.dto.ReservationRequest;
import com.mazen.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
