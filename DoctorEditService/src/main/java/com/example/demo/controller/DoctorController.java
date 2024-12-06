package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PutMapping("/doctor/{id}")
	public ResponseEntity<Doctor> editDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		return new ResponseEntity<Doctor>(doctorService.editDoctor(doctor, id), HttpStatus.OK);
	}
}
