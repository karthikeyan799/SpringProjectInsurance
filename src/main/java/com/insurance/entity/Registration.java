package com.insurance.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Registration {
	@Id
	@GeneratedValue(strategy = (GenerationType.IDENTITY))
	private int userId;

	@NotBlank
//	@Column(unique = true, nullable = false)
	private String eMail;
	@NotBlank
	@Column(unique = true, nullable = false)
//	@Column(nullable = false)
	private String userName;
	@NotBlank
	@Column(nullable = false)
	private String password;

//	@JsonManagedReference
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "r")
// 
//	private Login loginList;

	

	public int getUserId() {
		return userId;
	}

	public Registration(int userId, @NotBlank String eMail, @NotBlank String userName, @NotBlank String password) {
	super();
	this.userId = userId;
	this.eMail = eMail;
	this.userName = userName;
	this.password = password;
}

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
