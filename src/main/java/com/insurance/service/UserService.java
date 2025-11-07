package com.insurance.service;

import org.springframework.stereotype.Service;

import com.insurance.dto.LoginDTO;
import com.insurance.dto.RegistrationDTO;
import com.insurance.response.LoginResponse;

@Service
public interface UserService {
	String addEmployee(RegistrationDTO employeeDTO);

	LoginResponse loginUser(LoginDTO userLogin);
}
