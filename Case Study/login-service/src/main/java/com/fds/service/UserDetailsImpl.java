package com.fds.service;
 
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fds.model.Login;
 
 
public class UserDetailsImpl implements UserDetails{
 
	private static final long serialVersionUID = 1L;
 
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	public UserDetailsImpl( String username,String password,Collection<? extends GrantedAuthority> authorities ) {
 
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	public static UserDetailsImpl getUser(Login user) {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
		return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
 
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
 
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
 
}