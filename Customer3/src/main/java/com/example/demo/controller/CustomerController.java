package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CustomerService;
import com.example.demo.pojo.Customer;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
		customerService.getCustomerById(id);
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
