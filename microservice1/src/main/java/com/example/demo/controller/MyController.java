package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MyConfiguration;

@RestController
public class MyController {

	@Autowired
	MyConfiguration config;
	
	@Value("${custom.message}")
	String customMessage;
	
	@Value("${custom.text}")
	String customText;
	
	@GetMapping("/")
	public String getInfo() {
		System.out.println(customMessage);
		System.out.println(customText);
		return "Hello from: "+config.getName()+" reach me at: "+config.getPhoneno();
	}
}
