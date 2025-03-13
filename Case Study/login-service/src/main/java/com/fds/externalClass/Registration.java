package com.fds.externalClass;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
	@Id
    @Size(min = 5, max = 20, message = "Username should be between 5 and 20 characters")
    private String username;

    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;

    @NotBlank(message = "Role should not be blank")
    private String role;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 10, max = 12, message = "Phone number should be exactly 10 or 12 digits long")    
    private String phoneNumber;

    
    @NotBlank(message = "Country cannot be empty")
    private String country;

}
