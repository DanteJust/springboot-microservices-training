package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HospitalNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2187140300529419167L;

	public HospitalNotFoundException(String message) {
		super(message);
	}
	
	public HospitalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
