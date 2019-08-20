package com.mazen.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mazen.flightreservation.entities.User;
import com.mazen.flightreservation.repos.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		
		LOGGER.info("showRegistrationPage() ");

		return "login/registerUser";

	}

	@RequestMapping("/login")
	public String showLoginPage() {
		LOGGER.info("showLoginPage() ");

		return "login/login";

	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {

		LOGGER.info("register() " + user);

		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {

		LOGGER.info("inside Login() -- email : " + email);

		User user = userRepository.findByEmail(email);
		if (user != null && password != null && !password.trim().equals("") && user.getPassword().equals(password)) {

			return "findFlights";
		} else {
			LOGGER.info("inside Login()" + " user or password incorrect");
			modelMap.addAttribute("msg", "Invalid user name or password! please try again.");
		}

		return "login/login";
	}
}
