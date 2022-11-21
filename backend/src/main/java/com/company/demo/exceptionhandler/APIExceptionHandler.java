package com.company.demo.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.demo.exception.EntityExistsException;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.model.ErrorResponse;

@RestControllerAdvice
public class APIExceptionHandler {

	private static ErrorResponse errorResponse = new ErrorResponse();

	public static void updateErrorResponse() {
		errorResponse.setStatusCode(500L);
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setTimeStamp(LocalDateTime.now());
	}

	@ExceptionHandler(NoEntitiesException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleNoEntities(NoEntitiesException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("no entities");
		return ResponseEntity.status(500).body(errorResponse);
	}

	@ExceptionHandler(EntityExistsException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleEntityExists(EntityExistsException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("entity exists");
		return ResponseEntity.status(500).body(errorResponse);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("entity not found");
		return ResponseEntity.status(500).body(errorResponse);
	}

}
