package com.insurance.demo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//this is log4j package "import org.apache.log4j.Logger;"
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.insurance.dto.CustomerDTO;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;
import com.insurance.entity.Registration;
import com.insurance.service.CustomerService;
import com.insurance.service.PolicyService;
import com.insurance.service.RegistrationService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.insurance")
@EnableJpaRepositories("com.insurance.dao")
@EntityScan(basePackages = { "com.insurance.entity" })
//@springbootapplication(exclude=securityautoconfiguration.class)
public class InsuranceApplication {

	private static Logger logger = Logger.getLogger(InsuranceApplication.class);

	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = SpringApplication.run(InsuranceApplication.class, args);
		logger.debug("*****Insurance Application Started*****");
		
		CustomerService customerService = ctx.getBean(CustomerService.class);
//		customerService.generatExcel();
		PolicyService policyService = ctx.getBean(PolicyService.class);
		RegistrationService reg = ctx.getBean(RegistrationService.class);
		CustomerDTO customer = new CustomerDTO();
		Policy policy = new Policy();

		Registration register = new Registration();
		register.setUserName("kala");
		register.seteMail("kalao89ui@m");
		register.setPassword("123456");
//		reg.insert(register);
		// Task 1

		customer.setCustomerName("karth");
		customer.setCustomerAge(21);
		customer.setCity("chennai");
		customer.setCreaditLimit(80000d);
		customer.setGender("male");
		customer.seteMail("karthir54@gmail.com");
		customer.setPhoneNumber("8480968029");
		customer.setSalary(18000d);
		customer.setState("tamilnadu");

//		customerService.insert(customer);

//     Task 2 
		policy.setPolicyNumber("POL12345");
		policy.setPolicyHolder("John Doe");
		policy.setPolicyType("Life Insurance"); // ("yyyy-MM-dd")
		policy.setPolicyStartDate(LocalDate.of(2023, 1, 1));
		policy.setPolicyEndDate(LocalDate.of(2023, 12, 31));
		policy.setPolicyAmount(20000d);
		Customer cu = new Customer();
		cu.setCustomerId(1);
		policy.setCustomer(cu);

//		policyService.insert(policy);
//		policyService.fetchPolicy(3);
		// Task 3
		// Creating Policy
		Policy policy2 = new Policy();
		policy2.setPolicyAmount(40000);
		policy2.setPolicyEndDate(LocalDate.of(2030, 7, 21));
		policy2.setPolicyHolder("John");
		policy2.setPolicyNumber("POL1234");
		policy2.setPolicyType("Life Insurance");
		policy2.setPolicyStartDate(LocalDate.of(2023, 7, 8));

		Policy policy3 = new Policy();
		policy3.setPolicyAmount(45000);
		policy3.setPolicyEndDate(LocalDate.of(2030, 8, 21));
		policy3.setPolicyHolder("Mick");
		policy3.setPolicyNumber("12");
		policy3.setPolicyType("Health Insurance");
		policy3.setPolicyStartDate(LocalDate.of(2023, 8, 8));

		// Creating Customer

		Customer c2 = new Customer();

		c2.setCity("tambaram");
//		c2.setCreaditLimit(50000d);
		c2.setCustomerAge(24);
		c2.setCustomerName("velu");
		c2.seteMail("velu223@gmail.com");
		c2.setGender("male");
		c2.setPhoneNumber("9073647640");
		c2.setSalary(23000d);
//		c2.setState("tamilnadu");

		// Association Policy to Customer
		policy2.setCustomer(c2);
		policy3.setCustomer(c2);

		// Association Customer to Policy
		List<Policy> list = new ArrayList<Policy>();
		list.add(policy2);
		list.add(policy3);
		c2.setPolicy(list);

//		customerService.insert(c2);

		// Joins Process..
//		Customer c3 = customerService.fetchCustomer(1);
//		System.out.println("Customer: " + c3);
//
//		List<Policy> policyList = c3.getPolicy();
//		System.out.println("***printing policy***");
//		for (Policy pObj : policyList) {
//			System.out.println("Policy type :" + pObj.getPolicyType()); }

		// Transaction Propagation
		Policy p4 = new Policy();
		p4.setPolicyAmount(45000);
		p4.setPolicyEndDate(LocalDate.of(2030, 8, 21));
		p4.setPolicyHolder("Jhon");
		p4.setPolicyNumber("POL003");
		p4.setPolicyType("Home Insurance");
		p4.setPolicyStartDate(LocalDate.of(2023, 8, 8));

		Customer c4 = new Customer();

		c4.setCity("erode");
//		c4.setCreaditLimit(50000d);
		c4.setCustomerAge(26);
		c4.setCustomerName("Maala");
		c4.seteMail("maala2@gmail.com");
		c4.setGender("female");
		c4.setPhoneNumber("9073647349");
		c4.setSalary(23000d);
//		c4.setState("tamilnadu");

		p4.setCustomer(c4);

//		customerService.transactionPropagation(c4, p4);

//				  
//		List<Customer> list1 = customerService.findCustomerById(1); //
//		System.out.println("Customer Bases on Customer id----->" + list1);
//				  
//		List<Customer> listName = customerService.findByCustomerName("e"); //
//		System.out.println("Customer Bases on Customer Name------>"+listName);
//				 
//		List<CustomerCustomized> customerListCustomized = customerService.findByCustomerNameCustomized("karthi");
//				
//		for (CustomerCustomized cc : customerListCustomized) {
//			System.out.println("Customer Customized Date--->" + cc.getCustomerName() + "-" + cc.getCity());
//
//		}

		// NamedQuery //
//		System.out.println("Customer DATA ORDERBYNAME" + customerService.findAllOrderByName());

		// //INNER JOIN //
//		List<Customer> customerList = customerService.findCustomerWithPolicy(); //
//		System.out.println("Customer With Policy INNER JOIN " + customerList);
//
//		// //LEFT OUTER JOIN //
//		List<Customer> customerList1 = customerService.findCustomerWithPolicyLeft(); //
//		System.out.println("Customer With Policy LEFT OUTER JOIN" + customerList1);
//
//		// //RIGHT OUTER JOIN //
//		List<Customer> customerList2 = customerService.findCustomerWithPolicyRight(); //
//		System.out.println("Customer With Policy RIGHT OUTER JOIN" + customerList2);
//
//		// Customer Join
//		List<CustomerPolicyCustomized> customerPolicyList = customerService.findByCustomerPolicyCustomized();
//		for (CustomerPolicyCustomized cpc : customerPolicyList) {
//			System.out.println("Customized Joined Data By Using Joins---->" + cpc.getCustomerName() + "-"
//					+ cpc.getPhoneNumber() + "-" + cpc.getPolicyNumber() + "-" + cpc.getPolicyType());
//		}
//
//		Double c = customerService.findCustomerAverageAge();
//		System.out.println("Average Age is :"+c);
//
//		Double max = customerService.findMaximumCustomerSalary();
//		System.out.println("Maximum Age :"+max);
//
//		Integer count = customerService.findCustomersCount();
//		System.out.println("Total Count :"+count);
//
//		Double sum = customerService.findSumCustomerSalary();
//		System.out.println("Total Salary :"+sum);

	}

}
