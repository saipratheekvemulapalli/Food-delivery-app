package com.fds.registration.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.registration.model.Registration;

public interface RegistrationRepo extends JpaRepository<Registration	, String> {
	Registration findByUsername(String username);
}
