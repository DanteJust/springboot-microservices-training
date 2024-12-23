package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}
	
	public List<Doctor> getAllDoctorsByHospitalId(int hospitalId) {
		return doctorRepository.findByHospitalId(hospitalId);
	}
}
