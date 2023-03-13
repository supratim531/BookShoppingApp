package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Customer;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.service.CustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllCustomer() throws NoEntitiesException {
		List<Customer> customers = this.customerService.readAllCustomer();
		return ResponseEntity.status(200).body(customers);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readCustomerById(@PathVariable("id") String customerId) throws EntityNotFoundException {
		Customer customer = this.customerService.readCustomerById(customerId);
		return ResponseEntity.status(200).body(customer);
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id") String customerId, @RequestBody Customer customer)
			throws EntityNotFoundException {
		Customer updatedCustomer = this.customerService.updateCustomerById(customerId, customer);
		return ResponseEntity.status(201).body(updatedCustomer);
	}

}
