package com.fds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fds.exception.UserAlreadypresent;
import com.fds.exception.UserNotFoundException;
import com.fds.externalClass.Registration;
import com.fds.externalServices.RegistrationProxy;
import com.fds.model.Login;
import com.fds.repo.LoginRepository;
import com.fds.request.LoginRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceImpl {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private PasswordEncoder bcrytp;
	
	@Autowired
	RegistrationProxy registrationProxy;
	
	public List<Login> getAllUsers(){
		
		log.info("Get All Users Methods Started");
		List<Login> list= new ArrayList<>();
		List<Login> findAll =lr.findAll();
		for(Login login: findAll) {
			String role=login.getRole();
			if(role.equals("User")) {
				log.info("Inside the if condition of getAllAdmins method in ServiceImpl Class");
				list.add(login);
			}
		}
		return list;	
	}
	
	public Login getByUsername(String username) {
		log.info("Get getByUsername Method is started");
		Login login = lr.findByUsername(username);
		if(login ==null) {
			log.warn("Inside the if condition of getByUsername method in ServiceImpl Class" );
			throw new UserNotFoundException("No data is found by these username"+username);
		}else {
			log.info("Inside the else condition getByUsername method in ServiceImpl Class");
			return login;
		}
	}
	
	
	//new method is added...............................
	 public boolean validateLogin(String username, String rawPassword) {
	        log.info("Validate login method started");
	       Registration registration= registrationProxy.findByUsername(username);
	        if (registration == null) {
	            log.warn("User not found");
	            throw new UserNotFoundException("No data found for username: " + username);
	        } else {
	            // Compare the raw password with the hashed password stored in DB
	            return bcrytp.matches(rawPassword, registration.getPassword());
	        }
	    }
	 
	 public boolean verifyingUserAndPassword(LoginRequest loginRequest) {
		    Login lgUserName = lr.findByUsername(loginRequest.getUsername());
		    
		    if (lgUserName == null) {
		        throw new UserNotFoundException("username with : " + loginRequest.getUsername() + " doesn't exist. Please sign up.");
		    } else {
		        boolean isPasswordValid = bcrytp.matches(loginRequest.getPassword(), lgUserName.getPassword());
		        
		        if (!isPasswordValid) {
		            throw new UserNotFoundException("Please check your password.");
		        }
		    }
		    
		    return true;
		}

	
	public Login updateByUsername(String username, Login loginModel) {
		log.info("updateByUsername method started");
		Login login= lr.findByUsername(username);
		if(login == null) {
			log.warn("Inside the if condition of updateByUsername in ServiceImpl Class");
			throw new UserNotFoundException("No data is found by these "+loginModel);
		}else {
			log.info("Inside the else condition of updateByUsername method in ServiceImpl Class");
			
			//login.setPassword(bcrytp.encode(loginModel.getPassword()));
			loginModel.setPassword(login.getPassword());
			loginModel.setRole(login.getRole());
			Login updated = lr.save(loginModel);
			
			log.info("Admin updated successfully");
			return updated;
		}
		
	}
	
	public String updateByPassword(String username, Login loginModel) {
		log.info("updateByPassword method started");
		Login login= lr.findByUsername(username);
		if(login == null) {
			log.warn("Inside the if condition of updateByPassword in ServiceImpl Class");
			throw new UserNotFoundException("No data is found by these "+loginModel);
		}else {
			log.info("Inside the else condition of updateByUsername method in ServiceImpl Class");
			
			login.setPassword(bcrytp.encode(loginModel.getPassword()));
			lr.save(login);
			log.info("password updated successfully");
			return "Password changed";
		}
		
	}

	public Login addingUserFromRegis(Registration registration) throws UserAlreadypresent {
		
		 Login existingloginuser  = lr.findByUsername(registration.getUsername());
		 
		 if(existingloginuser!=null) {
			 throw new UserAlreadypresent("User Already present");
		 }
		 Login login = new Login();
			login.setUsername(registration.getUsername());
			login.setPassword(registration.getPassword());
			login.setRole(registration.getRole());
			login.setEmail(registration.getEmail());
			login.setPhoneNumber(Long.parseLong( registration.getPhoneNumber()));
			login.setCountry(registration.getCountry());
			return lr.save(login);
	}

}
