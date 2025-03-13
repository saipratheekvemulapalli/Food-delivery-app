package com.fds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fds.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>{
	Login findByUsername(String username);
	Login findByPassword(String password);
}
