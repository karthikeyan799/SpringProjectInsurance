package com.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PolicyExceptionHandler {
	@ExceptionHandler(value = { PolicyOKException.class })
	public ResponseEntity<Object> policyOKException(PolicyOKException ok) {
//		PolicyException exception = new PolicyException(ok.getMessage(),  HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(ok.getMessage());
	}

	@ExceptionHandler(value = { PolicyNotFoundException.class })
	public ResponseEntity<Object> policyNotFoundException(PolicyNotFoundException not) {
		PolicyException exception = new PolicyException(not.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { PolicyInternalServerException.class })
	public ResponseEntity<Object> policyInternalServerException(PolicyInternalServerException server) {
		PolicyException exception = new PolicyException(server.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { DuplicateValueException.class })
	public ResponseEntity<Object> duplicate(DuplicateValueException d) {
//		CustomerException customer = new CustomerException(d.getMessage(), HttpStatus.BAD_REQUEST);
//		return new ResponseEntity<>(customer, HttpStatus.CONFLICT);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(d.getMessage());
	}
}
