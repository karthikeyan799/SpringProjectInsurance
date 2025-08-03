package com.insurance.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


//@EntityListeners(AuditingEntityListener.class)
public class Login {
//	@Id
//	@GeneratedValue(strategy = (GenerationType.IDENTITY))
//	private int id;
//	@Column(unique = true, nullable = false)
//	private String username;
//	@Column(nullable = false)
//	private String password;
////	
////	@JsonBackReference
////	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
//	Registration r;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return username;
//	}

//	public void setUserName(String userName) {
//		this.username = userName;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	 
}
