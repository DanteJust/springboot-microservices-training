package com.example.demo.pojo;

import java.util.List;

import jakarta.persistence.Transient;

public class HospitalWithDoctors extends Hospital {
	@Transient
	private List<Doctor> doctors;
	
	public HospitalWithDoctors(Hospital hospital, List<Doctor> doctors) {
		this.setHospitalRegistrationId(hospital.getHospitalRegistrationId());
		this.setHospitalName(hospital.getHospitalName());
		this.setAddress(hospital.getAddress());
		this.setDoctors(doctors);
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	
}
