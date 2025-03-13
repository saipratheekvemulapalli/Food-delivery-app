package com.fds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fds.model.Login;
import com.fds.repo.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepository loginrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Login user= loginrepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found with username "+username);
		}
		return UserDetailsImpl.getUser(user);
	}

}
