package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Address;
import com.company.demo.entity.Customer;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.AddressRepository;
import com.company.demo.repository.CustomerRepository;
import com.company.demo.service.AddressService;
import com.company.demo.service.CustomerService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Address createAddress(String customerId, Address address) throws EntityNotFoundException {
		Customer customer = this.customerService.readCustomerById(customerId);
		address.setCustomer(customer);
		customer.getAddresses().add(address);
		List<Address> addresses = this.customerRepository.save(customer).getAddresses();
		Address createdAddress = addresses.get(addresses.size() - 1);
		return createdAddress;
	}

	@Override
	public List<Address> readAllAddress() throws NoEntitiesException {
		List<Address> addresses = this.addressRepository.findAll();

		if (addresses.size() > 0)
			return addresses;

		throw new NoEntitiesException("No address exists till now");
	}

	@Override
	public Address readAddressById(String addressId) throws EntityNotFoundException {
		Address address = this.addressRepository.findById(addressId)
				.orElseThrow(() -> new EntityNotFoundException("No address found with id: " + addressId));
		return address;
	}

	@Override
	public Address updateAddressById(String addressId, Address address) throws EntityNotFoundException {
		Address updatedAddress = this.readAddressById(addressId);
		updatedAddress.setName(address.getName());
		updatedAddress.setState(address.getState());
		updatedAddress.setCity(address.getCity());
		updatedAddress.setLocality(address.getLocality());
		updatedAddress.setAddressLine(address.getAddressLine());
		updatedAddress.setLandmark(address.getLandmark());
		updatedAddress.setPinCode(address.getPinCode());
		updatedAddress.setPhone(address.getPhone());
		updatedAddress.setAlternatePhone(address.getAlternatePhone());
		this.addressRepository.save(updatedAddress);
		return updatedAddress;
	}

	@Override
	public String deleteAddressById(String addressId) throws EntityNotFoundException {
		Address address = this.readAddressById(addressId);
		this.addressRepository.deleteById(addressId);
		String username = this.customerService.readCustomerById(address.getCustomer().getCustomerId()).getUser()
				.getUsername();
		return "Deleted address of user " + username;
	}

}
