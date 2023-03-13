package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Address;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface AddressService {

	public Address createAddress(String customerId, Address address) throws EntityNotFoundException;

	public List<Address> readAllAddress() throws NoEntitiesException;

	public Address readAddressById(String addressId) throws EntityNotFoundException;

	public Address updateAddressById(String addressId, Address address) throws EntityNotFoundException;

	public String deleteAddressById(String addressId) throws EntityNotFoundException;

}
