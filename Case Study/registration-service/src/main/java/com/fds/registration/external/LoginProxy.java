package com.fds.registration.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.registration.exception.UserAlreadypresent;
import com.fds.registration.model.Registration;



@FeignClient(name="login-service",url = "http://localhost:9991/registration")
public interface LoginProxy {
	
	
	@PostMapping("/addinguser")
	public Login addingUserFromRegis(@RequestBody Registration registration) throws UserAlreadypresent ;
}
