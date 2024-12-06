package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor getDoctorById(Long id) throws DoctorNotFoundException {
		return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor with that ID does not exist"));
	}
}
