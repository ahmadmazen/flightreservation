package com.mazen.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazen.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
