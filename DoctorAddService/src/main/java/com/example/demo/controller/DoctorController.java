package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping("/doctor")
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
		Doctor newDoctor = doctorService.createNewDoctor(doctor);
		return new ResponseEntity<Doctor>(newDoctor, HttpStatus.CREATED);
	}
}
