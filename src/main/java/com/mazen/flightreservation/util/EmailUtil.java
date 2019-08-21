package com.mazen.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;



@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${com.mazen.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;
	@Value("${com.mazen.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	public void sendItinerary(String toAddress, String filePath) {
		
		LOGGER.info("inside sendItinerary()");
		
       MimeMessage message = sender.createMimeMessage();
       
       
       try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject(EMAIL_SUBJECT);
			mimeMessageHelper.setText(EMAIL_BODY);
			mimeMessageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);
			
	} catch (MessagingException e) {
		LOGGER.error("Exception inside sendItinerary: " + e);
	}
		
		
		
	}
	

}
