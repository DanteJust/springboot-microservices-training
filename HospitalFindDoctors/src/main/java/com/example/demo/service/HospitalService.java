package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.HospitalNotFoundException;
import com.example.demo.pojo.Hospital;
import com.example.demo.repository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
	
	public Hospital getHospitalById(Long id) throws HospitalNotFoundException {
		Optional<Hospital> hospital = hospitalRepository.findById(id);
		if (!hospital.isPresent()) {
			throw new HospitalNotFoundException("Hospital with that id was not found!");
		}
		return hospital.get();
	}
}
