package com.insurance.dto;
 
	import java.io.Serializable;
	import java.util.Date;

	public class CustomerDTO implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 *
		 *
		 * @author Don Foe
		 */

		private int customerId;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String customerName;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private int customerAge;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String eMail;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String phoneNumber;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private double salary;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String gender;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String city;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private String state;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private double creaditLimit;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private Date createdAt;
		/**
		 *
		 *
		 * @author Don Foe
		 */
		private Date updatedAt;

		/**
		 *
		 *
		 * @author Don Foe
		 */

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

		public String getState() {
			return state;
		}

		public void setState(final String state) {
			this.state = state;
		}

		public double getCreaditLimit() {
			return creaditLimit;
		}

		public void setCreaditLimit(final double creaditLimit) {
			this.creaditLimit = creaditLimit;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(final Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(final Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		@Override
		public String toString() {
			return "CustomerDTO [customerId=" + customerId + ", customerName=" + customerName + ", customerAge="
					+ customerAge + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", gender="
					+ gender + ", city=" + city + ", state=" + state + ", creaditLimit=" + creaditLimit + ", createdAt="
					+ createdAt + ", updatedAt=" + updatedAt + "]";
		}

	}


