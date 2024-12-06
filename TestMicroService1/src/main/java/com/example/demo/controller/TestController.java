package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

	@GetMapping("/microservice2")
	public ResponseEntity<String> getMicroService2() {
		return new RestTemplate().getForEntity("http://localhost:8081/hello", String.class);
	}
}
