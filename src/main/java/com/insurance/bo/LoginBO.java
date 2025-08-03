package com.insurance.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insurance.dao.IRegistration;
import com.insurance.entity.Registration;
import com.insurance.exception.RegisterInternalServerException;

import jakarta.transaction.Transactional;

@Component
public class LoginBO {
	@Autowired
	IRegistration register;
//	 
	private static final Logger logger = Logger.getLogger(LoginBO.class);

	Registration reg;

	 
//    public Registration findByUsername(String userName) {
//        return register.findByUserName(userName);
//    }
    
	public Registration registration(Registration r) {
		if (r.getUserName().isBlank()) {
			throw new RegisterInternalServerException(r.getUserName() + " User Name is Empty");

		} else if (r.getUserName().matches(".*\\d.*")) {

			throw new RegisterInternalServerException(r.getUserName() + " User Name is without number");
		} else if (r.getPassword().length() < 6) {
			throw new RegisterInternalServerException(r.getUserName() + " Password is minimum 6 letter");
		}  
		return register.save(r);
	}

//	public Registration findByName(String username) {
//		// TODO Auto-generated method stub
//		return register.findByUserName(username);
//	}
//	public Registration findByUserNameAndPassword(String username) {
//		return register.
//	}
}
