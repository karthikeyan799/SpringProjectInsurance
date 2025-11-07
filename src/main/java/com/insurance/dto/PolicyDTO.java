package com.insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class PolicyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int policyId;
	private String policyHolder;
	private String policyType;
	private String policyNumber;
	private double policyAmount;
	private LocalDate policyStartDate;
	private LocalDate policyEndDate;
	private int customerId;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(String policyHolder) {
		this.policyHolder = policyHolder;
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

	public double getPolicyAmount() {
		return policyAmount;
	}

	public void setPolicyAmount(double policyAmount) {
		this.policyAmount = policyAmount;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "PolicyDTO [policyId=" + policyId + ", policyHolder=" + policyHolder + ", policyType=" + policyType
				+ ", policyNumber=" + policyNumber + ", policyAmount=" + policyAmount + ", policyStartDate="
				+ policyStartDate + ", policyEndDate=" + policyEndDate + ", customerId=" + customerId + "]";
	}
}
