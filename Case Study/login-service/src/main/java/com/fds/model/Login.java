package com.fds.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Login {
	
	@Id
    private String username;
    private String password;
    private String role;
    private long phoneNumber;
    private String email;
    private String country;
	

}
