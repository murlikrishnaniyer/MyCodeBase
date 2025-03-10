package com.example.springbootk8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SpringBootK8sController {
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
