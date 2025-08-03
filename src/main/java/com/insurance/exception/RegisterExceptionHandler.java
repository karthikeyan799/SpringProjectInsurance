package com.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterExceptionHandler {
	@ExceptionHandler(value = { RegisterNotFoundException.class })
	public ResponseEntity<Object> registerNotFoundExceptionHandler(RegisterNotFoundException register) {
		RegisterException exception = new RegisterException(register.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {RegisterInternalServerException.class })
	public ResponseEntity<String> registerInternalServerException(RegisterInternalServerException server) {
//	CustomerException customer = new CustomerException(server.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	return new ResponseEntity<>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(server.getMessage());
	}

	@ExceptionHandler(value = { RegisterOkException.class })
	public ResponseEntity<String> registerOKException(RegisterOkException ok) {
//	PolicyException exception = new PolicyException(ok.getMessage(),  HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(ok.getMessage());
	}

	@ExceptionHandler(value = { RegisterBadRequest.class })
	public ResponseEntity<Object> badRequest(RegisterBadRequest bad) {
		RegisterException register = new RegisterException(bad.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(register, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { DuplicateValueException.class })
	public ResponseEntity<Object> duplicate(DuplicateValueException d) {
//	CustomerException customer = new CustomerException(d.getMessage(), HttpStatus.BAD_REQUEST);
//	return new ResponseEntity<>(customer, HttpStatus.CONFLICT);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(d.getMessage());
	}
}
