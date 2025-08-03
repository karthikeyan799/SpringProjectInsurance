package com.insurance.response;

public class Response {
 
private String successMessage;
private String failureMessage;
private Double double1;
private Integer integer;
 
public Double getDouble1() {
	return double1;
}
public void setDouble1(Double double1) {
	this.double1 = double1;
}
public Integer getInteger() {
	return integer;
}
public void setInteger(Integer integer) {
	this.integer = integer;
}
public String getSuccessMessage() {
	return successMessage;
}
public void setSuccessMessage(String successMessage) {
	this.successMessage = successMessage;
}
public String getFailureMessage() {
	return failureMessage;
}
public void setFailureMessage(String failureMessage) {
	this.failureMessage = failureMessage;
}
 
}
