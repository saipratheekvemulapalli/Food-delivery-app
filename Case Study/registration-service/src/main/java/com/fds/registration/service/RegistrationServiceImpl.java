package com.fds.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fds.registration.exception.RegistrationException;
import com.fds.registration.exception.UserAlreadypresent;
import com.fds.registration.external.LoginProxy;
import com.fds.registration.model.Registration;
import com.fds.registration.repository.RegistrationRepo;


@Service
public class RegistrationServiceImpl {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private RegistrationRepo registrationRepo;
	
	@Autowired
	LoginProxy loginProxy;
	
	public Registration save(Registration user) throws UserAlreadypresent {
		Registration existingUser= registrationRepo.findByUsername(user.getUsername());
		
		if (existingUser != null) {
			// User with the same username already exists, throw an exception
			throw new RegistrationException("Username already exists: " + user.getUsername());
		}
		Registration login = new Registration();
		login.setUsername(user.getUsername());
		login.setPassword(bcryptEncoder.encode(user.getPassword()));
		login.setRole(user.getRole());
		login.setEmail(user.getEmail());
		login.setPhoneNumber(user.getPhoneNumber());
		login.setCountry(user.getCountry());
		
		loginProxy.addingUserFromRegis(login);
		
		return registrationRepo.save(login);
	}

	public ResponseEntity<Registration> findByUsername(String username) {
		
		Registration registration = registrationRepo.findByUsername(username);
		if(registration==null) {
			throw new RegistrationException("User not found");
		}
		return new ResponseEntity<>(registration,HttpStatus.OK);
		
	}

}
