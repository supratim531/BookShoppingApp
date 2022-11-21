package com.company.demo.exception;

@SuppressWarnings("serial")
public class EntityExistsException extends Exception {

	public EntityExistsException(String message) {
		super(message);
	}

}
