package com.example.springbootk8s.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SpringBootK8sController {
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
