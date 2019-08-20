package com.mazen.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mazen.flightreservation.entities.Flight;
import com.mazen.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	
	@RequestMapping("/showFindFlights")
	public String showFindFlightsPage() {
		
		return "findFlights";
		
	}
	

	@RequestMapping("findFlights")
	// ,@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date
	// departureDate
	public String findFlight(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") String departureDate,
			ModelMap modelMap) {
		LOGGER.info("findFlight() -- From:" + from + " -- " + " To:" + to + " -- " + "Departure Date:" + departureDate);

		
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		System.out.println(flights);

		return "displayFlights";

	}
	
	
	
}
