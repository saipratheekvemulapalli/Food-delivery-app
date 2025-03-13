package com.fds.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.exception.BadCredentialsException;
import com.fds.exception.UserAlreadypresent;
import com.fds.externalClass.Registration;
import com.fds.jwt.JwtUtility;
import com.fds.model.Login;
import com.fds.request.LoginRequest;
import com.fds.response.JSONResponse;
import com.fds.service.ServiceImpl;
import com.fds.service.UserDetailsImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class AuthController {
	
	@Autowired 
	DaoAuthenticationProvider authenticationManagaer;
	
//	@Autowired
//	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtUtility jwtUtility;
	
	@Autowired
	ServiceImpl serviceImpl;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser (@Valid @RequestBody LoginRequest loginRequest) throws BadCredentialsException{
		//added some lines in method start................
//		boolean isPasswordValid = serviceImpl.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
//	    if (!isPasswordValid) {
//	        throw new BadCredentialsException("Invalid credentials");
//	    }
	    // end......................
		boolean verifyusernameandpassword = serviceImpl.verifyingUserAndPassword(loginRequest);
		
		if(!verifyusernameandpassword) {
			throw new BadCredentialsException("Invalid credentials");
		}
	    
		Authentication authentication = authenticationManagaer.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()) );
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		String jwtToken = jwtUtility.generateToken(authentication);
		Collection<? extends GrantedAuthority> authorites = userDetails.getAuthorities();
		List<String> roles = authorites.stream()
											.map(GrantedAuthority :: getAuthority)
											.collect(Collectors.toList());
		JSONResponse jsonResponse = new JSONResponse(jwtToken, userDetails.getUsername(), roles);
		return ResponseEntity.ok(jsonResponse);
	}
	
	@PostMapping("/addinguser")
		public Login addingUserFromRegis(@RequestBody Registration registration) throws UserAlreadypresent {
			return serviceImpl.addingUserFromRegis(registration);
		}
	

}
