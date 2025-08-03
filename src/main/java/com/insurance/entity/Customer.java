package com.insurance.entity;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull; 
@Entity
@Table(name = "Customer")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
		@NamedQuery(name = "Customer.findAllOrderByNameDescending", query = "select c from Customer c ORDER BY c.customerName DESC") })

/**
 *
 *
 * @author Don Doc
 */
public class Customer {

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@Id
	@GeneratedValue(strategy = (GenerationType.IDENTITY))
	private int customerId;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotBlank
	@Column(name = "customerName")
	private String customerName;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotNull
	private int customerAge;
	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotBlank
//	@Column(unique = true)
	private String eMail;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotBlank
//	@Column(unique = true, name = "phoneNumber")
	private String phoneNumber;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotNull
	private double salary;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotBlank
	private String gender;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@NotBlank
	private String city;

	/**
	 *
	 *
	 * @author Don Doc
	 */
//	@NotBlank
//	private String state;

	/**
	 *
	 *
	 * @author Don Doc
	 */
//	@NotNull
//	private double creaditLimit;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date createdAt;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Policy> policy;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(final int customerAge) {
		this.customerAge = customerAge;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(final String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(final double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

//	public String getState() {
//		return state;
//	}
//
//	public void setState(final String state) {
//		this.state = state;
//	}

	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Policy> getPolicy() {
		return policy;
	}

	public void setPolicy(final List<Policy> policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAge=" + customerAge
				+ ", eMail=" + eMail + ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", gender=" + gender
				+ ", city=" + city + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
