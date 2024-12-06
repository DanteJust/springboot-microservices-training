package com.example.demo.controller;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.feign.HospitalDoctorFeign;
import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;
import com.example.demo.pojo.HospitalWithDoctors;
import com.example.demo.service.HospitalService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@Autowired
	HospitalDoctorFeign feignClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/hospital/{id}")
	public ResponseEntity<HospitalWithDoctors> getHospitalById(@PathVariable Long id) {
		Hospital hospitalWithoutDoctors = hospitalService.getHospitalById(id);
		ResponseEntity<Doctor[]> entity = restTemplate.getForEntity("http://doctorgetallservice/doctors/{id}", Doctor[].class, id);
		HospitalWithDoctors hospital = new HospitalWithDoctors(hospitalWithoutDoctors, Arrays.asList(entity.getBody()));
		return new ResponseEntity<HospitalWithDoctors>(hospital, HttpStatus.OK);
	}
	
	@GetMapping("/hospital-feign/{id}")
	@CircuitBreaker(name="circuit-breaker-for-doctor", fallbackMethod="myMethod")
	public ResponseEntity<HospitalWithDoctors> getHospitalByIdFeign(@PathVariable Long id) {
		Hospital hospitalWithoutDoctors = hospitalService.getHospitalById(id);
		ResponseEntity<List<Doctor>> entity = feignClient.findAllDoctorsByHospitalId(id);
		HospitalWithDoctors hospital = new HospitalWithDoctors(hospitalWithoutDoctors, entity.getBody());
		return new ResponseEntity<HospitalWithDoctors>(hospital, HttpStatus.OK);
	}
	
	public ResponseEntity<HospitalWithDoctors> myMethod(Long id, Throwable e) {
		return new ResponseEntity<HospitalWithDoctors>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hospitals-rate-limiter/{id}")
	@RateLimiter(name="rate-limiter-for-doctor", fallbackMethod="getDoctorFallbackRateLimiter")
	public ResponseEntity<HospitalWithDoctors> getHospitalByIdRateLimiter(@PathVariable Long id) {
		Hospital hospitalWithoutDoctors = hospitalService.getHospitalById(id);
		ResponseEntity<List<Doctor>> entity = feignClient.findAllDoctorsByHospitalId(id);
		HospitalWithDoctors hospital = new HospitalWithDoctors(hospitalWithoutDoctors, entity.getBody());
		return new ResponseEntity<HospitalWithDoctors>(hospital, HttpStatus.OK);
	}
	
	public ResponseEntity<HospitalWithDoctors> getDoctorFallbackRateLimiter(Long id, Throwable e) {
		return new ResponseEntity<HospitalWithDoctors>(HttpStatus.TOO_MANY_REQUESTS);
	}
	
	@GetMapping("/hospitals-bulk-head/{id}")
	@Bulkhead(name="bulkhead-for-doctor", fallbackMethod="getDoctorFallbackBulkHead")
	public ResponseEntity<HospitalWithDoctors> getHospitalByIdBulkHead(@PathVariable Long id) {
		Hospital hospitalWithoutDoctors = hospitalService.getHospitalById(id);
		ResponseEntity<List<Doctor>> entity = feignClient.findAllDoctorsByHospitalId(id);
		HospitalWithDoctors hospital = new HospitalWithDoctors(hospitalWithoutDoctors, entity.getBody());
		return new ResponseEntity<HospitalWithDoctors>(hospital, HttpStatus.OK);
	}
	
	public ResponseEntity<HospitalWithDoctors> getDoctorFallbackBulkHead(Long id, Throwable e) {
		return new ResponseEntity<HospitalWithDoctors>(HttpStatus.TOO_MANY_REQUESTS);
	}
}
