package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.Customer;

@Service
public class CustomerService {
	private List<Customer> customers = new ArrayList<Customer>();
	
	public CustomerService() {
		customers.add(new Customer(1L, "customer1", "customer1@gmail.com"));
		customers.add(new Customer(2L, "customer2", "customer2@gmail.com"));
		customers.add(new Customer(3L, "customer3", "customer3@gmail.com"));
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Optional<Customer> findById(Long id) {
		return customers.stream().filter(c->c.getId().equals(id)).findFirst();
	}
	
	public Customer saveCustomer(Customer customer) {
		customers.add(customer);
		return customer;
	}
	
	public boolean deleteById(Long id) {
		return customers.removeIf(c->c.getId().equals(id));
	}
	
	public Optional<Customer> update(Long id, Customer customer) {
		Optional<Customer> existingCustomer = findById(id);
		
		if (existingCustomer.isPresent()) {
			existingCustomer.get().setName(customer.getName());
			existingCustomer.get().setEmail(customer.getEmail());
		}
		return existingCustomer;
	}
}
