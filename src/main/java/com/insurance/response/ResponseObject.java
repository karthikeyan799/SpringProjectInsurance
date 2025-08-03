package com.insurance.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.insurance.dao.CustomerCustomized;
import com.insurance.dao.CustomerPolicyCustomized;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;

@Component
public class ResponseObject {
	private String successMessage;
	private String failureMessage;
	private Customer customer;
	private Policy policy;
	private List<Customer> listCustomer;
	private List<Policy> listPolicy;
	private List<CustomerPolicyCustomized> customerPolicyCustomized;

	 
	public List<CustomerPolicyCustomized> getCustomerPolicyCustomized() {
		return customerPolicyCustomized;
	}

	public void setCustomerPolicyCustomized(List<CustomerPolicyCustomized> customerPolicyCustomized) {
		this.customerPolicyCustomized = customerPolicyCustomized;
	}

	private List<CustomerCustomized> customized;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	 

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public List<Policy> getListPolicy() {
		return listPolicy;
	}

	public void setListPolicy(List<Policy> listPolicy) {
		this.listPolicy = listPolicy;
	}

	public List<CustomerCustomized> getCustomized() {
		return customized;
	}

	public void setCustomized(List<CustomerCustomized> customized) {
		this.customized = customized;
	}
}
