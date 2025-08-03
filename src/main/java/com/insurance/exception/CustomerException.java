package com.insurance.exception;

import org.springframework.http.HttpStatus;

public class CustomerException {

	private final String message;
//	private final Throwable throwable;
	private final HttpStatus httpStatus;
	public CustomerException(String message,  HttpStatus httpStatus) {
		super();
		this.message = message;
//		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	 
	  
	 
}
