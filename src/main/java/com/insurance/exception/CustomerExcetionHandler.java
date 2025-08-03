package com.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExcetionHandler {
	@ExceptionHandler(value = { CustomerNotFoundException.class })
	public ResponseEntity<Object> customerNotFoundexceptionHandle(CustomerNotFoundException customerNotFoundException) {

		CustomerException customerException = new CustomerException(customerNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(customerException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { CustomerInternalServerException.class })
	public ResponseEntity<String> customerInternalServerException(CustomerInternalServerException server) {
//		CustomerException customer = new CustomerException(server.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		return new ResponseEntity<>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(server.getMessage());
	}

	@ExceptionHandler(value = { CustomerOKException.class })
	public ResponseEntity<String> customerOKException(CustomerOKException ok) {
//		PolicyException exception = new PolicyException(ok.getMessage(),  HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(ok.getMessage());
	}

	@ExceptionHandler(value = { CustomerBadRequestException.class })
	public ResponseEntity<Object> badRequest(CustomerBadRequestException bad) {
		CustomerException customer = new CustomerException(bad.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { DuplicateValueException.class })
	public ResponseEntity<Object> duplicate(DuplicateValueException d) {
//		CustomerException customer = new CustomerException(d.getMessage(), HttpStatus.BAD_REQUEST);
//		return new ResponseEntity<>(customer, HttpStatus.CONFLICT);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(d.getMessage());
	}

}
