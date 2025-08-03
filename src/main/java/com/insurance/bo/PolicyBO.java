package com.insurance.bo;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insurance.dao.PolicyRepository;
import com.insurance.entity.Policy;
import com.insurance.exception.CustomerInternalServerException;
import com.insurance.exception.DuplicateValueException;
import com.insurance.exception.PolicyInternalServerException;
import com.insurance.exception.PolicyNotFoundException;
import com.insurance.exception.PolicyOKException;
import com.insurance.response.ResponseObject;

@Component
public class PolicyBO {
	@Autowired
	PolicyRepository policyr;
	ResponseObject object;
	private static final Logger logger = Logger.getLogger(PolicyBO.class);

	public Policy insert(Policy p) throws DuplicateValueException{
		String id="POL";
		Policy policyNumber=policyr.findByPolicyNumber(p.getPolicyNumber());
		if (p.getPolicyAmount() < 20000) {
			throw new PolicyInternalServerException(p.getPolicyAmount() + " Policy Amount is Minimum 20000");
		} else if (p.getPolicyAmount() > 100000) {
			throw new PolicyInternalServerException(p.getPolicyAmount() + " Oops! is Policy Amount is Maximum 100000");
		} else if (p.getPolicyHolder().matches(".*\\d.*")) {
			throw new CustomerInternalServerException(p.getPolicyHolder() + " Policy Holder is Can't number value.");
		} else if (p.getPolicyHolder().isBlank() && !p.getPolicyHolder().matches(".*\\d.*")) {
			throw new PolicyInternalServerException(p.getPolicyHolder() + " Policy Holder is Empty");
		} else if (p.getPolicyType().isBlank() && !p.getPolicyType().matches(".*\\d.*")) {
			throw new PolicyInternalServerException(p.getPolicyType() + " Policy Type is Empty");
		} else if (p.getPolicyType().matches(".*\\d.*")) {
			throw new PolicyInternalServerException(p.getPolicyType() + " Policy Type is Can't number value.");
//		}else if(p.getPolicyId()!=p.getPolicyId()) {
//			throw new DataIntegrityViolationException(p.getPolicyId()+" Duplicate value");
		}else if(id.concat(p.getPolicyNumber()) == null) {
			throw new PolicyInternalServerException(p.getPolicyNumber()+" Policy Number is empty");
//		}else if(policyNumber!=null) {
//			throw new DuplicateValueException(p.getPolicyNumber()+" Policy Number is already exists");
		}
		return policyr.save(p);
//		return object;
	}

	public Policy update(Policy id) {
		return policyr.save(id);
	}

	public Policy fetchPolicy(Integer id) {
		Optional<Policy> opy = policyr.findById(id);
		if (policyr.findById(id).isEmpty()) {
			logger.error("Request Policy id is does not exist ");
			throw new PolicyInternalServerException(id + " Requested Policy Id is does not Exist...");
		}
		return opy.get();
	}

	public List<Policy> fetchAll() {
		if (policyr.findAll().isEmpty()) {
			logger.error("Policy table is empty");
			throw new PolicyInternalServerException("Policy Table is empty!!!");
		}
		return policyr.findAll();
	}

	public Double findPolicyAverageAmount() {
		if (policyr.findPolicyAverageAmount() != null) {
			logger.info("Average Policy Amount is:" + policyr.findPolicyAverageAmount());
			throw new PolicyOKException("Average Policy Amount is:" + policyr.findPolicyAverageAmount());
		}
		return policyr.findPolicyAverageAmount();
	}

	public Double findMaximumPolicyAmount() {
		if (policyr.findMaximumPolicyAmount() != null) {
			logger.info("Maximum Amount is :" + policyr.findMaximumPolicyAmount());
			throw new PolicyOKException("Maximum Amount is :" + policyr.findMaximumPolicyAmount());
		}
		return policyr.findMaximumPolicyAmount();

	}

	public Integer findPolicyCount() {
		if (policyr.findPolicyCount() != null) {
			logger.info("Total Policy is :" + policyr.findPolicyCount());
			throw new PolicyOKException("Total Policy is :" + policyr.findPolicyCount());
		}
		return policyr.findPolicyCount();
	}

	public Double findSumPolicyAmount() {
		if (policyr.findSumPolicyAmount() != null) {
			logger.info("Total Policy Amount is :" + policyr.findSumPolicyAmount());
			throw new PolicyOKException("Total Policy Amount is :" + policyr.findSumPolicyAmount());
		}
		return policyr.findSumPolicyAmount();
	}

	public Double findMinimumPolicyAmount() {
		if (policyr.findMinimumPolicyAmount() != null) {
			logger.info("Minimum Policy Amount is :" + policyr.findMinimumPolicyAmount());
			throw new PolicyOKException("Minimum Policy Amount is :" + policyr.findMinimumPolicyAmount());
		}
		return policyr.findMinimumPolicyAmount();

	}

	public List<String> getGroupedByPolicyType() {

		if (policyr.getGroupedByPolicyType() != null) {
			logger.info("GroupedBy Policy Type is :" + policyr.getGroupedByPolicyType());

			throw new PolicyOKException("GroupedBy Policy Type is  :" + policyr.getGroupedByPolicyType());
		}
		return policyr.getGroupedByPolicyType();

	}
}
