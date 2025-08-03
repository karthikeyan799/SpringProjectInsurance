package com.insurance.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.bo.LoginBO;
import com.insurance.dao.IRegistration;
import com.insurance.dto.LoginDTO;
import com.insurance.dto.RegistrationDTO;
import com.insurance.entity.Registration;
import com.insurance.exception.RegisterInternalServerException;
import com.insurance.response.LoginResponse;
import com.insurance.response.RegisterResponse;

@Service
public class RegistrationService implements UserService {

	Logger logger = Logger.getLogger(RegistrationService.class);
	@Autowired
	private LoginBO login;

	@Autowired
	IRegistration register;

//	@Autowired
//	private PasswordEncoder passwordEncoded;
//	public Registration getUserUsername(String username) {
//        return register.findByName(username);
//    }
//	  @Transactional
//	    public ObjectResponse<Registration> authenticateDoctorLogin(String username, String password) {
//		  ObjectResponse<Registration> responseObject = new ObjectResponse<>();
//	        
//		  Registration doctorLogin = register.findByUserName(username);
//
//	        if (doctorLogin != null && doctorLogin.getPassword().matches(password)) {
//	            responseObject.setSuccess(true);
//	            responseObject.setMessage("Doctor login successful.");
//	            responseObject.setData(doctorLogin);
//	        } else {
//	            responseObject.setSuccess(false);
//	            responseObject.setMessage("Invalid credentials.");
//	        }
//
//	        return responseObject;
//	    }
//	
	public RegisterResponse insert(final Registration dto) {
		RegisterResponse responseObject = new RegisterResponse();
		Registration register = new Registration();
		try {
			register.setUserId(dto.getUserId());
			register.setUserName(dto.getUserName());
//			register.setPhoneNumber(dto.getPhoneNumber());
			register.seteMail(dto.geteMail());
			register.setPassword(dto.getPassword());
//			register.setConfirmPassword(dto.getConfirmPassword());

			Registration register2 = login.registration(register);
			logger.info(register2);
			logger.info(register2.getUserId() + " Register Successfully...");

			responseObject.setRegistered(register2);
			responseObject.setSuccessMessage(register2.getUserId() + " is Register successfully..");

		} catch (RegisterInternalServerException r) {
			logger.error("Error When Registered... " + r);
			responseObject.setFailureMessage(r.getMessage());

		}
		return responseObject;

	}

	RegistrationDTO registration;

	@Override
	public LoginResponse loginUser(LoginDTO userLogin) {
		// TODO Auto-generated method stub
//		  String msg = "";

//		Registration employee1 = register.findByeMail(userLogin.geteMail());
//		Registration employee1=register.findByeMail(userLogin.getUserName());
        Registration employee1 = register.findByUserName(userLogin.getUserName());
		if (employee1 != null) {
			String password = userLogin.getPassword();
			String encodedPassword = employee1.getPassword();
//	            Boolean isPwdRight = passwordEncoded.matches(password, encodedPassword);

			Boolean isPwdRight = password.matches(encodedPassword);
			if (isPwdRight) {
//				Optional<Registration> employee = register.findOneByeMailAndPassword(userLogin.geteMail(),
//						encodedPassword);
	           Optional<Registration> employee = register.findOneByUserNameAndPassword(userLogin.getUserName(), encodedPassword);
//				Optional<Registration> employee=register.findOneByeMailAndPassword(userLogin.getUserName(), encodedPassword);
				if (employee.isPresent()) {
					
					return new LoginResponse("Login Success", true);
				} else {
					return new LoginResponse("Login Failed", false);
				}
			} else {
				return new LoginResponse("password Not Match", false);
			}

		} else {
			return new LoginResponse("Email not exits", false);
		}

	}

	@Override
	public String addEmployee(RegistrationDTO employeeDTO) {
		// TODO Auto-generated method stub
		Registration employee = new Registration(employeeDTO.getUserId(), employeeDTO.geteMail(),
				employeeDTO.getUserName(),
//	                this.passwordEncoded.encode(employeeDTO.getPassword())
				employeeDTO.getPassword());
		register.save(employee);
		return employee.getUserName();
	}

	RegistrationDTO employeeDTO;

}
