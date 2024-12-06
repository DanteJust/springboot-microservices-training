package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.pojo.Customer;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return customerRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		customerRepository.saveCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		return customerRepository.updateCustomer(id, customer) == 1
				? ResponseEntity.ok(customer)
				: ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		return customerRepository.deleteById(id) == 1
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
}