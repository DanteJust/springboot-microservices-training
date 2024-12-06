package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public List<Doctor> getDoctors(@RequestHeader(name="sort", defaultValue="all") String sort) {
		System.out.println("Request header received: " + sort);
		return doctorService.getAllDoctors();
	}
	
	@GetMapping("/doctors/{hospital_id}")
	public ResponseEntity<List<Doctor>> getDoctorsByHospitalId(@PathVariable int hospital_id) {
		List<Doctor> doctors = doctorService.getAllDoctorsByHospitalId(hospital_id);
		if (doctors.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		}
		return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
	}
}
