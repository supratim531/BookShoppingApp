package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Customer;
import com.company.demo.entity.User;
import com.company.demo.exception.EntityExistsException;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.UserRepository;
import com.company.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws EntityExistsException {
		if (this.userRepository.findById(user.getUsername()).orElse(null) != null)
			throw new EntityExistsException("Username " + user.getUsername() + " is already registered");

		Customer customer = new Customer(null, null, null, null, null, user);
		user.setCustomer(customer);
		user.setUserRole("ROLE_" + user.getUserRole());
		User createdUser = this.userRepository.save(user);
		return createdUser;
	}

	@Override
	public List<User> readAllUser() throws NoEntitiesException {
		List<User> users = this.userRepository.findAll();

		if (users.size() > 0)
			return users;

		throw new NoEntitiesException("No user exists till now");
	}

	@Override
	public User readUserById(String username) throws EntityNotFoundException {
		User user = this.userRepository.findById(username)
				.orElseThrow(() -> new EntityNotFoundException("No user found with username: " + username));
		return user;
	}

	@Override
	public String deleteUserById(String username) throws EntityNotFoundException {
		this.readUserById(username);
		this.userRepository.deleteById(username);
		return "Deactivated account of user " + username;
	}

}
