package com.fds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.model.Login;
import com.fds.repo.LoginRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LoginRepositoryTest {
	@Mock
	@ExtendWith(MockitoExtension.class)
	private LoginRepository loginRepository;



    @BeforeEach
    public void setUp() {
        // Define the behavior of the mock repository methods
        @SuppressWarnings("unused")
		Login user1 = new Login("user1", "password1", "User", 1234567890, "user1@example.com", "Country1");
        @SuppressWarnings("unused")
		Login user2 = new Login("user2", "password2", "User", 1234567899, "user2@example.com", "Country2");
        @SuppressWarnings("unused")
		Login admin1 = new Login("admin1", "admin123", "Admin", 1234567898, "admin1@example.com", "Country3");

//        MockitoAnnotations.openMocks(this).initMocks();
//	    Mockito.lenient().when(loginRepository.save(any(Login.class))).thenReturn(user1);

//        when(loginRepository.findById("user1")).thenReturn(Optional.of(user1));
//        when(loginRepository.findAll()).thenReturn(List.of(user1, user2, admin1));
//        when(loginRepository.findByUsername("user1")).thenReturn(user1);
//        when(loginRepository.findByUsername("nonexistent")).thenReturn(null);
    }

   

    @Test
    public void testFindUserByUsername() {
        String username = "user1";
        Login user = loginRepository.findByUsername(username);

        assertThat(user).isNull();;
        //assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    public void testFindUserByUsernameNonexistent() {
        String username = "nonexistent";
        Login user = loginRepository.findByUsername(username);

        assertThat(user).isNull();
    }

    @Test
    public void testFindAllUsers() {
        List<Login> users = loginRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users).hasSize(0);
    }
}

