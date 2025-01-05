package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "index"; // Maps to index.html in the templates folder
	}

	@GetMapping("/create-message")
	public String createMessage() {
		return "create-message"; // Serves the create-message.html page
	}

	@GetMapping("/manage-messages")
	public String manageMessages() {
		return "manage-messages"; // This returns the manage-messages.html page
	}

}
