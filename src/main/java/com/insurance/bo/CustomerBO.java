package com.insurance.bo;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insurance.dao.CustomerCustomized;
import com.insurance.dao.CustomerPolicyCustomized;
import com.insurance.dao.CustomerRepository;
import com.insurance.dto.CustomerDTO;
import com.insurance.entity.Customer;
import com.insurance.exception.CustomerInternalServerException;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.CustomerOKException;

@Component
public class CustomerBO {
	@Autowired
	CustomerRepository customRep;

	Customer custom;
	private static final Logger logger = Logger.getLogger(CustomerBO.class);

	public Customer insert(Customer c) throws CustomerInternalServerException {
		String male = "male";
		String female = "female";
		if (c.getCustomerName().isBlank() && !c.getCustomerName().matches(".*\\d.*")) {

//		if (c.getCustomerName().isBlank()) {
			throw new CustomerInternalServerException(c.getCustomerName() + " customer Name is Empty");

		} else if (c.getCustomerName().matches(".*\\d.*")) {

			throw new CustomerInternalServerException(c.getCustomerName() + " customer Name is without number");
		} else if (c.getCustomerName().length() < 2) {
			throw new CustomerInternalServerException(c.getCustomerName() + " Sorry CustomerName is Minimum 3 letter");
		} else if (c.getCustomerName().length() > 13) {
			throw new CustomerInternalServerException(c.getCustomerName() + " Oops! CustomerName is Maximum 13 letter");

		}

		else if (!c.getPhoneNumber().trim().matches("^\\d{10}$")) {
			throw new CustomerInternalServerException(c.getPhoneNumber() + " Phone Number is 10 Digit value");

		} else if (c.getPhoneNumber() == null) {
			throw new CustomerInternalServerException(c.getPhoneNumber() + " Phone Number Is Blank");
		} else if (c.geteMail().matches("\\.com$")) {
			throw new CustomerInternalServerException(c.geteMail() + " .com is missing");
		} else if (c.geteMail().matches("^[A-Za-z0-9._%+-]+@gmail")) {
			throw new CustomerInternalServerException(c.geteMail() + "--- " + " .com is missing");
		}

		if (c.geteMail() != null && !c.geteMail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")) {
			throw new CustomerInternalServerException(c.geteMail() + " Invalid email ");
		} else if (c.getCustomerAge() < 18) {

			throw new CustomerInternalServerException(c.getCustomerAge() + " Customer Age is Invalid");
		} else if (c.getSalary() < 15000)

		{
			throw new CustomerInternalServerException("Salary is minimum 15,000.");

		} else if (c.getSalary() > 50000) {
			throw new CustomerInternalServerException("Salary is maximum 80,000.");

		}

//		else if (c.getCreaditLimit() < 30000) {
////				throw new CustomerException("Oops.. CreaditLimit is max 80,000");
//			throw new CustomerInternalServerException("Sorry. Creadit Limit is min 30,000");
//
//		} else if (c.getCreaditLimit() > 80000) {
//			throw new CustomerInternalServerException("Oops.. CreaditLimit is max 80,000");
//		}
		else if (c.getCity().isBlank() && !c.getCity().matches(".*\\d.*")) {

			throw new CustomerInternalServerException(c.getCity() + " City  is Empty");

		} else if (c.getCity().matches(".*\\d.*")) {

			throw new CustomerInternalServerException(c.getCity() + " City Name is without number");
		} else if (c.getCity().length() < 3) {
			throw new CustomerInternalServerException(c.getCity() + " Oops! city is Minimum 4 letter");
		}else if(c.getGender()=="") {
			throw new CustomerInternalServerException(c.getGender() + " gender is required");
		}

		return customRep.save(c);

	}

	public Customer update(Customer id) {
//			if(id.geteMail()) {
//				
//			}

		return customRep.save(id);
	}

	public Customer findCustomer(int id)     {
		Optional<Customer> cus = customRep.findById(id);
		if (customRep.findById(id).isEmpty()) {
			logger.error("fetch Customer Id failure");
			throw new CustomerNotFoundException(id + " Requested Customer Id is does not Exist!!!");
		}
		return cus.get();
//		Customer customer = customRep.findById(id).orElse(null);
//		if (customer == null) {
//			throw new CustomerNotFoundException(id + " Request id is does not exist in database");
//
//		}
//		return customer;
	}

	public List<Customer> findCustomerById(int customerId1) {

		if (customRep.findCustomerById(customerId1).isEmpty()) {

			logger.error("finds Customer By Id :" + customRep.findCustomerById(customerId1));
			throw new CustomerNotFoundException(customerId1 + " is invalid id");
		}
		return customRep.findCustomerById(customerId1);
	}

	public List<Customer> findCustomers(){
		if (customRep.findAll().isEmpty()) {
			logger.error("Find All Customers :" + customRep.findAll());
			throw new CustomerInternalServerException("Customer Table is does not exist!!!");
		}
		return customRep.findAll();
//		return 
		
	}

	public List<Customer> findByCustomerName(String name) {
		if (customRep.findByName(name).isEmpty()) {
			logger.error("Customer By Name :" + customRep.findByName(name));
			throw new CustomerInternalServerException(name + " is invalid letter");
		}
		return customRep.findByName(name);
	}

	public List<CustomerCustomized> findByCustomerNameCustomized(String name1) {
		if (customRep.findByCustomerNameCustomized(name1).isEmpty()) {

			logger.error("Customer Name Customized :" + customRep.findByCustomerNameCustomized(name1));
			throw new CustomerInternalServerException(name1 + " Customer name is does not exist in database!!!");
		}
		return customRep.findByCustomerNameCustomized(name1);
	}

	public List<Customer> findAllOrderByName() {
		logger.info("All Order By Name :" + customRep.findAllOrderByNameDescending());
		return customRep.findAllOrderByNameDescending();
	}

	public List<Customer> findCustomerWithPolicyRight() {
		// TODO Auto-generated method stub
		logger.info("Customer Right Outer Join :" + customRep.findCustomerWithPolicyRight());

		return customRep.findCustomerWithPolicyRight();
	}

	public List<Customer> findCustomerWithPolicyLeft() {
		// TODO Auto-generated method stub
		logger.info("Customer Left Outer Join :" + customRep.findCustomerWithPolicyLeft());
		return customRep.findCustomerWithPolicyLeft();
	}

	public List<CustomerPolicyCustomized> findByCustomerPolicyCustomized() {
		// TODO Auto-generated method stub
		logger.info("By Customer Policy Customized join" + customRep.findByCustomerPolicyCustomized());
		return customRep.findByCustomerPolicyCustomized();
	}

	public List<Customer> findCustomerWithPolicy() {
		// TODO Auto-generated method stub
		logger.info("Inner join" + customRep.findCustomerWithPolicy());
		return customRep.findCustomerWithPolicy();
	}

	public Double findCustomerAverageAge() {
		if (customRep.findCustomerAverageAge() == null) {
			logger.info("Average Age is :" + customRep.findCustomerAverageAge());
			throw new CustomerNotFoundException("Avarage Age is :" + customRep.findCustomerAverageAge());

		}

		return customRep.findCustomerAverageAge();
	}

	public Double findMaximumCustomerSalary() {
		if (customRep.findMaximumCustomerSalary() == null) {
			logger.info("Maximum Customer Salary is :" + customRep.findMaximumCustomerSalary());
			throw new CustomerOKException("Maximum Age is :" + customRep.findMaximumCustomerSalary());
		}
		return customRep.findMaximumCustomerSalary();
	}

	public Integer findCustomersCount() {
		if (customRep.findCustomersCount() == null) {
			logger.info("Total Customer  is :" + customRep.findCustomersCount());

			throw new CustomerOKException("Total Customer is :" + customRep.findCustomersCount());
		}
		return customRep.findCustomersCount();
	}

	public Double findSumCustomerSalary() {
		if (customRep.findSumCustomerSalary() == null) {
			logger.info("Total Customer Salary is :" + customRep.findSumCustomerSalary());

			throw new CustomerOKException("Total Customer Salary is :" + customRep.findSumCustomerSalary());
//		System.out.println("Total Customer Salary is :" + customRep.findSumCustomerSalary());
		}
		return customRep.findSumCustomerSalary();
	}

	public Double findMinimumCustomerSalary() {
		if (customRep.findMinimumCustomerSalary() == null) {
			logger.info("Minimum Customer Salary is :" + customRep.findMinimumCustomerSalary());

			throw new CustomerOKException("Minimum Customer salary is :" + customRep.findMinimumCustomerSalary());
		}
		return customRep.findMinimumCustomerSalary();
	}

	public List<String> getAverageSalaryByCity() {
		if (customRep.getAverageSalaryByCity() != null) {
			logger.info("GroupedBy Average Customer Salary By City :" + customRep.getAverageSalaryByCity());

			throw new CustomerOKException("GroupedBy Average salary By City :" + customRep.getAverageSalaryByCity());

		}
		return customRep.getAverageSalaryByCity();

	}

	

}
