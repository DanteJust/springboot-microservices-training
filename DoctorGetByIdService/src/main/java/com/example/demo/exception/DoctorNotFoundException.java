package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7740410170380760131L;

	public DoctorNotFoundException(String message) {
		super(message);
	}
	
	public DoctorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
