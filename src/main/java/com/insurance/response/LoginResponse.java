package com.insurance.response;

public class LoginResponse{
//public LoginResponse(String string, boolean b) {
//		super();
//		// TODO Auto-generated constructor stub
//	}
private boolean status;
private String message;
public LoginResponse( String message,boolean status) {
	super();
	this.message = message;
	this.status = status;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}}