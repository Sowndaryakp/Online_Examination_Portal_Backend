package com.java.backend;

import com.java.backend.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.java.backend")
public class BackendApplication {

//	@Autowired
//	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		senderService.sendSimpleEmail("toemail@gmail.com",
//				"SMTP message",
//				"Successfully send message");
//
//	}
}
