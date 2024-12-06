package com.example.demo.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hospital")
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hospitalRegistrationId;
	private String address;
	private String hospitalName;
	
	public Hospital() {
		
	}
	
	public Hospital(Long hospitalRegistrationId, String address, String hospitalName) {
		super();
		this.hospitalRegistrationId = hospitalRegistrationId;
		this.address = address;
		this.hospitalName = hospitalName;
	}
	
	public Long getHospitalRegistrationId() {
		return hospitalRegistrationId;
	}
	public void setHospitalRegistrationId(Long hospitalRegistrationId) {
		this.hospitalRegistrationId = hospitalRegistrationId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	
}
