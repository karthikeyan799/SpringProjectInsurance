package com.insurance.response;
import com.insurance.entity.Login;
import com.insurance.entity.Registration;
 
public class RegisterResponse {

	private String successMessage;
	private String failureMessage;
	private Registration registered;
	private Login login;
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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

	public Registration getRegistered() {
		return registered;
	}

	public void setRegistered(Registration registered) {
		this.registered = registered;
	}

	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return false;
	}
}
