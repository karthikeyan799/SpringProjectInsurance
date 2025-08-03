package com.insurance.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Registration;

@Repository
public interface IRegistration extends JpaRepository<Registration,Integer> {
//	Optional<Registration> findByEmailAndPassword(String eMail,String password);
//	String confirm(String eMail);
	Optional<Registration> findOneByeMailAndPassword(String email, String password);
	Optional<Registration> findOneByUserNameAndPassword(String name, String password);
    Registration findByeMail(String name );
    Registration findByUserName(String string);
}
