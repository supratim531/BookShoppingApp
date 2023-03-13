package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Customer;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.CustomerRepository;
import com.company.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> readAllCustomer() throws NoEntitiesException {
		List<Customer> customers = this.customerRepository.findAll();

		if (customers.size() > 0)
			return customers;

		throw new NoEntitiesException("No customer exists till now");
	}

	@Override
	public Customer readCustomerById(String customerId) throws EntityNotFoundException {
		Customer customer = this.customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException("No customer found with id: " + customerId));
		return customer;
	}

	@Override
	public Customer updateCustomerById(String customerId, Customer customer) throws EntityNotFoundException {
		Customer updatedCustomer = this.readCustomerById(customerId);
		updatedCustomer.setFirstName(customer.getFirstName());
		updatedCustomer.setLastName(customer.getLastName());
		updatedCustomer.setGender(customer.getGender());
		updatedCustomer.setPhone(customer.getPhone());
		updatedCustomer.setEmail(customer.getEmail());
		this.customerRepository.save(updatedCustomer);
		return updatedCustomer;
	}

}
