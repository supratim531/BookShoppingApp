package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Address;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.service.AddressService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/add")
	public ResponseEntity<?> createAddress(@RequestParam Long customerId, @RequestBody Address address)
			throws EntityNotFoundException {
		Address createdAddress = this.addressService.createAddress(customerId, address);
		return ResponseEntity.status(201).body(createdAddress);
	}

	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllAddress() throws NoEntitiesException {
		List<Address> addresses = this.addressService.readAllAddress();
		return ResponseEntity.status(200).body(addresses);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readAddressById(@PathVariable("id") Long addressId) throws EntityNotFoundException {
		Address address = this.addressService.readAddressById(addressId);
		return ResponseEntity.status(200).body(address);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> updateAddressById(@PathVariable("id") Long addressId, @RequestBody Address address)
			throws EntityNotFoundException {
		Address updatedAddress = this.addressService.updateAddressById(addressId, address);
		return ResponseEntity.status(201).body(updatedAddress);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAddressById(@PathVariable("id") Long addressId) throws EntityNotFoundException {
		String message = this.addressService.deleteAddressById(addressId);
		return ResponseEntity.status(200).body(message);
	}

}
