package com.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	@Query("SELECT AVG(p.policyAmount) FROM Policy p")
	Double findPolicyAverageAmount();

	@Query("SELECT MAX(p.policyAmount) FROM Policy p")
	Double findMaximumPolicyAmount();

	@Query("SELECT COUNT(p.policyNumber) FROM Policy p")
	Integer findPolicyCount();

	@Query("SELECT SUM(p.policyAmount) FROM Policy p")
	Double findSumPolicyAmount();

	@Query("SELECT MIN(p.policyAmount) FROM Policy p")
	Double findMinimumPolicyAmount();

	// HAVING CLAUSE
	@Query(value = "select p.policyType as policyType, AVG(p.policyAmount) as amount from Policy as p group by p.policyType")
	List<String> getGroupedByPolicyType();

	Policy findByPolicyNumber(String policyNumber);
}
