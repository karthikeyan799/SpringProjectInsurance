package com.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.dto.CustomerDTO;
import com.insurance.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>  {

	@Query("SELECT AVG(c.customerAge) FROM Customer c")
	Double findCustomerAverageAge();

	@Query("SELECT MAX(c.salary) FROM Customer c")
	Double findMaximumCustomerSalary();

	@Query("SELECT COUNT(c.customerAge) FROM Customer c")
	Integer findCustomersCount();

	@Query("SELECT SUM(c.salary) FROM Customer c")
	Double findSumCustomerSalary();

	@Query("SELECT MIN(c.salary) FROM Customer c")
	Double findMinimumCustomerSalary();

	// HAVING CLAUSE
	@Query(value = "select c.city as name, AVG(c.salary) as salary from Customer as c group by c.city having avg(c.salary)>25000")
	List<String> getAverageSalaryByCity();

	@Query(value = "select c from Customer c where c.customerId>:customerId1")
	List<Customer> findCustomerById(@Param("customerId1") long customerId);

	@Query(value = "select c from Customer c where c.customerName LIKE %:customerName1%")
	List<Customer> findByName(@Param("customerName1") String customerName);

	// Fetches only few columns from Customer
	@Query("select c.city as city,c.eMail as email,c.customerName as customerName from Customer c where c.customerName=:customerName1")
	List<CustomerCustomized> findByCustomerNameCustomized(@Param("customerName1") String customerName);

	// Named Query
	List<Customer> findAllOrderByNameDescending();

//	// Inner Query
	@Query("select c from Customer c JOIN policy p on c.customerId=p.customer")
	List<Customer> findCustomerWithPolicy();

//	 Left Outer Join
	@Query("select c from Customer c LEFT OUTER JOIN Policy p on c.customerId=p.customer")
	List<Customer> findCustomerWithPolicyLeft();

	// Right Outer Join
	@Query("select c from Customer c Right OUTER JOIN Policy p on c.customerId=p.customer")
	List<Customer> findCustomerWithPolicyRight();

	// Customized date by join
	@Query("select c.customerName as customerName,c.eMail as email,c.phoneNumber as phoneNumber,p.policyNumber as policyNumber,p.policyType as policyType from Customer c JOIN Policy p on c.customerId=p.customer")
	List<CustomerPolicyCustomized> findByCustomerPolicyCustomized();
//
//	boolean existsByCustomerName(String customerName);
//
//	boolean existsByeMail(String customerName);

	 
}
