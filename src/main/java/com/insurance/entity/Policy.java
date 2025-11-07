package com.insurance.entity;
 
	import java.time.LocalDate;
import java.util.Date;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

	@Entity
	@Table(name = "Policy")
	@EntityListeners(AuditingEntityListener.class)
	public class Policy {
		@Id
		@GeneratedValue(strategy = (GenerationType.IDENTITY))
		private int policyId;

		@NotBlank
		private String policyType;

		@NotBlank
//		@Column(unique = true)
		private String policyNumber;

		@NotBlank
		private String policyHolder;

		@NotNull
		private LocalDate policyStartDate;

		@NotNull
		private LocalDate policyEndDate;

		@CreatedDate
		@Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false, updatable = false)
		private Date createdAt;

		@Column(nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		@LastModifiedDate
		private Date updatedAt;

		@NotNull
		private double policyAmount;

		@JsonBackReference
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "customerId", nullable = false, referencedColumnName = "customerId")
		private Customer customer;

		public int getPolicyId() {
			return policyId;
		}

		public void setPolicyId(int policyId) {
			this.policyId = policyId;
		}

		public String getPolicyType() {
			return policyType;
		}

		public void setPolicyType(String policyType) {
			this.policyType = policyType;
		}

		public String getPolicyNumber() {
			return policyNumber;
		}

		public void setPolicyNumber(String policyNumber) {
			this.policyNumber = policyNumber;
		}

		public String getPolicyHolder() {
			return policyHolder;
		}

		public void setPolicyHolder(String policyHolder) {
			this.policyHolder = policyHolder;
		}

		public LocalDate getPolicyStartDate() {
			return policyStartDate;
		}

		public void setPolicyStartDate(LocalDate policyStartDate) {
			this.policyStartDate = policyStartDate;
		}

		public LocalDate getPolicyEndDate() {
			return policyEndDate;
		}

		public void setPolicyEndDate(LocalDate policyEndDate) {
			this.policyEndDate = policyEndDate;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public double getPolicyAmount() {
			return policyAmount;
		}

		public void setPolicyAmount(double policyAmount) {
			this.policyAmount = policyAmount;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		@Override
		public String toString() {
			return "Policy [policyId=" + policyId + ", policyType=" + policyType + ", policyNumber=" + policyNumber
					+ ", policyHolder=" + policyHolder + ", policyStartDate=" + policyStartDate + ", policyEndDate="
					+ policyEndDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", policyAmount="
					+ policyAmount + ", customer=" + customer + "]";
		}

	}


