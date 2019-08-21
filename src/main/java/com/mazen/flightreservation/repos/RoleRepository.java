package com.mazen.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazen.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
