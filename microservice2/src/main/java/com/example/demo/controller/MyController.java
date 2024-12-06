package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@Value("${custom.text}")
	String customText;
	
	@GetMapping("/")
	public String getInfo() {
		return customText;
	}
}
