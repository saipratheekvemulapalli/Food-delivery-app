package com.fds.externalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.externalClass.Registration;
import com.fds.model.Login;

import jakarta.validation.Valid;

@FeignClient(name = "registration-service" ,url = "http://localhost:9990/register")
public interface RegistrationProxy {
	
	
	

		
	@GetMapping("/getuserbyname/{username}")
	public Registration findByUsername(@PathVariable String username);
	
	
}
