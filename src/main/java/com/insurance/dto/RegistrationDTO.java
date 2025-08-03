package com.insurance.dto;

public class RegistrationDTO {

	public RegistrationDTO() {
		
		// TODO Auto-generated constructor stub
	}

	public RegistrationDTO(int userId, String eMail, String userName, String password) {
		
		this.userId = userId;
		this.userName = userName;
		this.eMail = eMail;
		this.password = password;
	}

	private int userId;
	private String userName;
	private String eMail;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
