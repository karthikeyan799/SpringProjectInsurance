package com.insurance.exception;

import org.springframework.http.HttpStatus;

public class RegisterException {
	private final String message;
	private final HttpStatus httpStatus;

	public RegisterException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
