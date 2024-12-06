package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	StreamBridge bridge;
	
	@PostMapping(path = "/doctors", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Doctor> createNewDoctorRecord(@RequestBody Doctor doctor) {
		Doctor newDoctor = doctorService.createDoctor(doctor);
		boolean isPublished = bridge.send("doctorSupplier-out-0", MessageBuilder.withPayload("doctor added").build());
		System.out.println("Data published: " + isPublished);
		return new ResponseEntity<Doctor>(newDoctor, HttpStatus.OK);
	}
}
