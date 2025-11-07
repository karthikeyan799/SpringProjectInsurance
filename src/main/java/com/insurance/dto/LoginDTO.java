package com.insurance.dto;

public class LoginDTO {
//private String userName;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String eMail;

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	private String password;

//public String getUserName() {
//	return userName;
//}
//public void setUserName(String userName) {
//	this.userName = userName;
//}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
