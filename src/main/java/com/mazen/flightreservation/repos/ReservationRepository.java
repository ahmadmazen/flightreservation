package com.mazen.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazen.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
