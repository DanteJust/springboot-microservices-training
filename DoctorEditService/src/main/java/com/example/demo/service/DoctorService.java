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
	
	public Doctor editDoctor(Doctor doctor, Long id) throws DoctorNotFoundException {
		Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor with that ID was not found"));
		existingDoctor.setDoctorName(doctor.getDoctorName());
		existingDoctor.setHospitalId(doctor.getHospitalId());
		existingDoctor.setSpecialization(doctor.getSpecialization());
		return doctorRepository.save(existingDoctor);
	}
}
