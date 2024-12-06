package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleCustomerException(DoctorNotFoundException ex) {
		Map<String, String> returnMessage = new HashMap<>();
		returnMessage.put("message", ex.getMessage());
		return new ResponseEntity<>(returnMessage, HttpStatus.NOT_FOUND);
	}
}
