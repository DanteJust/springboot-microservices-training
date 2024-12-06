package com.example.demo.controller;

import java.util.ArrayList;



import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.Doctor;
//import com.example.demo.repo.HospitalRepository;

@RestController
public class DoctorController {

	List<Doctor> doctors_received = new ArrayList<>();

	@Bean
	Consumer<String> readDoctors() {
		System.out.println("***********received doctor details**********");

		return (doctor) -> {
			System.out.println("Inside the consumer");
//			doctors_received.add(doctor);
			System.out.println("Consumer Received : " + doctor);
		};
	}

}