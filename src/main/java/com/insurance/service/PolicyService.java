package com.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.insurance.bo.CustomerBO;
import com.insurance.bo.PolicyBO;
import com.insurance.dto.PolicyDTO;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.PolicyInternalServerException;
import com.insurance.response.ResponseObject;
import com.insurance.rest.CustomerPolicyService;

@Service
public class PolicyService {

	private static Logger logger = Logger.getLogger(CustomerPolicyService.class);
	@Autowired
	CustomerBO customerBo;
	@Autowired
	PolicyBO policyBo;

	@Autowired
	ResponseObject object;

	public ResponseObject insert(final Policy dto) {
		ResponseObject responseObject = new ResponseObject();

		try {
//			Customer response=customerBo.findCustomer(dto.getCustomerId());
//			Policy policy=new Policy();
//			policy.setPolicyAmount(dto.getPolicyAmount());
//			policy.setPolicyEndDate(dto.getPolicyEndDate());
//			policy.setPolicyHolder(dto.getPolicyHolder());
//			policy.setPolicyNumber(dto.getPolicyNumber());
//			policy.setPolicyStartDate(dto.getPolicyEndDate());
//			policy.setPolicyType(dto.getPolicyType());
//			policy.setCustomer(response);
//	 return policyBo.insert(policy);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
			Customer response = customerBo.findCustomer(dto.getCustomer().getCustomerId());
//		return null;
			Policy policy1 = new Policy();
			String id = String.valueOf(dto.getPolicyId());
			String concateId = "POL2-".concat(id);
			policy1.setPolicyType(dto.getPolicyType());
			policy1.setPolicyHolder(dto.getPolicyHolder());
//			policy1.setPolicyNumber(concateId);
			String num=policyNumber(dto.getPolicyId());
			policy1.setPolicyNumber(num);
			policy1.setPolicyAmount(dto.getPolicyAmount());
			policy1.setPolicyStartDate(dto.getPolicyStartDate());
			policy1.setPolicyEndDate(dto.getPolicyEndDate());

			policy1.setCustomer(response);
			Policy polic = policyBo.insert(policy1);

			if (logger.isInfoEnabled()) {

				logger.info("Policy Id " + policy1.getPolicyId() + " Added Successfully...");
			}
			responseObject.setSuccessMessage(policy1.getPolicyId() + " Added Successfully object...");
			responseObject.setPolicy(polic);

		} catch (PolicyInternalServerException r) {
			if (logger.isDebugEnabled()) {
				logger.error(dto.getPolicyId() + " Error When Adding..." + r);
			}
			responseObject.setFailureMessage(r.getMessage());
//			System.out.println(policy.getPolicyId() + " Error When  Adding Policy...");

		} catch (DataIntegrityViolationException e) {
			if (logger.isDebugEnabled()) {
				logger.error(dto.getPolicyId() + " Duplicate value error.." + e);
			}
			if (dto.getPolicyNumber() != null) {
//				throw new DuplicateValueException(p.getPolicyNumber()+" Policy Number is already exists");

				responseObject.setFailureMessage("Duplicate Violation... Please check PolicyNumber");
			}
		}
//		return object;
		return responseObject;
	}

	public String policyNumber(int number) {
		Policy policy = new Policy();
		String num=String.valueOf(number);
		num=policy.getPolicyId()+policy.getPolicyNumber();
		return num;
	}

	public ResponseObject update(final Policy policy) {
		ResponseObject responseObject = new ResponseObject();
		try {

			Customer response = customerBo.findCustomer(policy.getCustomer().getCustomerId());
			Policy policy1 = policyBo.insert(policy);
			policy1.setPolicyId(policy.getPolicyId());
			policy1.setPolicyType(policy.getPolicyType());
			policy1.setPolicyHolder(policy.getPolicyHolder());
			policy1.setPolicyNumber("POL" + policy.getPolicyNumber());
			policy1.setPolicyAmount(policy.getPolicyAmount());
			policy1.setPolicyStartDate(policy.getPolicyStartDate());
			policy1.setPolicyEndDate(policy.getPolicyEndDate());
//			policy1.setCustomer(policy.getCustomer().getCustomerId());

			policy.setCustomer(response);
			if (logger.isInfoEnabled()) {

				logger.info("Policy Id " + policy.getPolicyId() + " Update Successfully...");
			}
			responseObject.setSuccessMessage(policy.getPolicyId() + " Update Successfully...");
			responseObject.setPolicy(policy1);

		} catch (PolicyInternalServerException r) {
			if (logger.isDebugEnabled()) {
				logger.error(policy.getPolicyId() + " Error When Update..." + r);
			}
			responseObject.setFailureMessage(r.getMessage());
//			System.out.println(policy.getPolicyId() + " Error When  Adding Policy...");

		} catch (DataIntegrityViolationException e) {
			if (logger.isDebugEnabled()) {
				logger.error(policy.getPolicyId() + " Duplicate value error.." + e);
			}
			if (policy.getPolicyNumber() != null) {
//				throw new DuplicateValueException(p.getPolicyNumber()+" Policy Number is already exists");
//
				responseObject.setFailureMessage("Duplicate Violation... Please check PolicyNumber");
			}
		}
		return responseObject;
	}

	public ResponseObject fetchPolicy(final int policyId) {
		logger.debug("Fetch Policy Id Is Triggered");
		ResponseObject responseObject = new ResponseObject();
		try {

			Policy policy = policyBo.fetchPolicy(policyId);

			if (policy != null) {

				policy.getCreatedAt();
				policy.getUpdatedAt();
				policy.getPolicyId();
				policy.getPolicyHolder();
				policy.getPolicyNumber();
				policy.getPolicyType();
				policy.getPolicyAmount();
				policy.getPolicyEndDate();
				policy.getPolicyStartDate();
				policy.getCustomer().getCustomerId();
				if (logger.isInfoEnabled()) {
					logger.info(policyId + " Policy ID is fetched successfully..");
					logger.info(policy);
				}
				responseObject.setPolicy(policy);
				responseObject.setSuccessMessage(policyId + " Policy ID is fetched successfully..");

			}

		} catch (PolicyInternalServerException r) {
//			if(policyId) {

//			}
			if (logger.isInfoEnabled()) {
				logger.error("PolicyId is does not match please call admin." + r);
			}
			responseObject.setFailureMessage(policyId + "  PolicyId is does not match ");
//			System.out.println("PolicyId is does not match ");
		}
		return responseObject;
	}

	public ResponseObject fetchAllPolicy() {
		logger.info("Fetch All is Triggered");
		ResponseObject responseObject = new ResponseObject();
		Policy policy = new Policy();
		List<Policy> listPolicy = new ArrayList<Policy>();

		try {
			List<Policy> list = policyBo.fetchAll();
			if (list != null) {

				for (Policy policyList : list) {

					policy.setPolicyId(policyList.getPolicyId());
					policy.setPolicyNumber(policyList.getPolicyNumber());
					policy.setPolicyHolder(policyList.getPolicyHolder());
					policy.setPolicyType(policyList.getPolicyType());
					policy.setPolicyAmount(policyList.getPolicyAmount());
					policy.setPolicyStartDate(policyList.getPolicyStartDate());
					policy.setPolicyEndDate(policyList.getPolicyEndDate());
					policy.setCreatedAt(policyList.getCreatedAt());
					policy.setUpdatedAt(policyList.getUpdatedAt());
//					System.out.println(object.getSuccessMessage());
//					listPolicy.add(policy);
					listPolicy.add(policyList);
				}
			}
//			ResponseObject obj=policyBo.fetchAll();
//			Policy list=obj.getListPolicy();
			logger.info("Fetch All method is successfully..");
//			logger.info(list);
			logger.info(listPolicy);
			responseObject.setSuccessMessage("Fetch All method is successfully...");
//			responseObject.setListPolicy(list);
			responseObject.setListPolicy(listPolicy);
//			System.out.println(list);

		} catch (CustomerNotFoundException e) {
			logger.error("Fetch All Records doed not exist in the tabel" + e);
			responseObject.setFailureMessage("Retreive Policy  does not exist in the list " + e);
		}
//		return policyBo.fetchAll();
		return responseObject;

	}

	public Double findPolicyAverageAmount() {
		logger.info("Average Policy Amount  is successfull");
		object.setSuccessMessage("Average Policy is" + policyBo.findPolicyAverageAmount());
		return policyBo.findPolicyAverageAmount();

	}

	public Double findMaximumPolicyAmount() {
		logger.info("Maximum Policy Amount  is Successfull");
		object.setSuccessMessage("Maximum Policy Amount  is :" + policyBo.findMaximumPolicyAmount());
		return policyBo.findMaximumPolicyAmount();

	}

	public Double findMinimumPolicyAmount() {
		logger.info("Minimum Policy Amount  is Successfull");
		object.setSuccessMessage("Minimum Policy Amount  is :" + policyBo.findMinimumPolicyAmount());
		return policyBo.findMinimumPolicyAmount();

	}

	public Double findSumPolicyAmount() {
		logger.info("Total Amount of Policy is Successfull");
		object.setSuccessMessage("Total Amount of Policy is :" + policyBo.findSumPolicyAmount());
		return policyBo.findSumPolicyAmount();

	}

	public Integer findPolicyCount() {
		logger.info("Policy Count  is Successfull");
		object.setSuccessMessage("Policy Count  is :" + policyBo.findPolicyCount());
		return policyBo.findPolicyCount();

	}

	public List<String> getGroupedByPolicyType() {
		logger.info("GroupedBy Policy Type Successfull");
		object.setSuccessMessage("GroupedBy Policy Type:" + policyBo.getGroupedByPolicyType());
		return policyBo.getGroupedByPolicyType();

	}

}
