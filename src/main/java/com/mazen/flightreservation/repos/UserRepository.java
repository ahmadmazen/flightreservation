package com.mazen.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazen.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
