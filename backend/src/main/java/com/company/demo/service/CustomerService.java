package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Customer;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface CustomerService {

	public List<Customer> readAllCustomer() throws NoEntitiesException;

	public Customer readCustomerById(String customerId) throws EntityNotFoundException;

	public Customer updateCustomerById(String customerId, Customer customer) throws EntityNotFoundException;

}
