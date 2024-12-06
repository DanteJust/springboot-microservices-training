package com.example.demo.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.exception.CustomerException;
import com.example.demo.pojo.Customer;;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new CustomerException("Customer with this id is not found");
		}
		return customer.get();
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
}
