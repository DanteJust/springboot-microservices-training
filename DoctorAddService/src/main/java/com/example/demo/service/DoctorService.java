package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor createNewDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
}
