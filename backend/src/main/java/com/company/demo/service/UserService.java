package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.User;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.exception.EntityExistsException;
import com.company.demo.exception.EntityNotFoundException;

public interface UserService {

	public User createUser(User user) throws EntityExistsException;

	public List<User> readAllUser() throws NoEntitiesException;

	public User readUserById(String username) throws EntityNotFoundException;

	public String deleteUserById(String username) throws EntityNotFoundException;

}
