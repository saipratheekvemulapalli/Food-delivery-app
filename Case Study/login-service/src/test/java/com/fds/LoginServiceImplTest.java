package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fds.exception.UserNotFoundException;
import com.fds.model.Login;
import com.fds.repo.LoginRepository;
import com.fds.service.ServiceImpl;

public class LoginServiceImplTest {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<Login> userList = new ArrayList<>();
        Login user1 = new Login("user1", "password1", "User", 1234567890, "user1@example.com", "Country");
        Login user2 = new Login("user2", "password2", "Admin", 1234567899, "user2@example.com", "Country");
        userList.add(user1);
        userList.add(user2);

        Mockito.when(loginRepository.findAll()).thenReturn(userList);

        List<Login> result = service.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("User", result.get(0).getRole());
    }

   

    @Test
    public void testGetByUsername() {
        String username = "user";
        Login user = new Login(username, "password", "User", 1234567890, "user@example.com", "Country");

        Mockito.when(loginRepository.findByUsername(username)).thenReturn(user);

        Login result = service.getByUsername(username);

        assertEquals(username, result.getUsername());
    }

  

    @Test
    public void testUpdateByUsernameUserNotFound() {
        String username = "user1";
        Login updatedUser = new Login(username, "newPassword", "User", 1234567899, "user1@example.com", "Country");

        Mockito.when(loginRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            service.updateByUsername(username, updatedUser);
        });
    }

    @Test
    public void testUpdateByPassword() {
        String username = "user1";
        Login existingUser = new Login(username, "oldPassword", "User", 1234567890, "user1@example.com", "Country");
        Login updatedUser = new Login(username, "newPassword", "User", 1234567890, "user1@example.com", "Country");

        Mockito.when(loginRepository.findByUsername(username)).thenReturn(existingUser);
        Mockito.when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");
        Mockito.when(loginRepository.save(any())).thenReturn(updatedUser);

        String result = service.updateByPassword(username, updatedUser);

        assertNotNull(result);
        assertEquals("Password changed", result);
    }

    @Test
    public void testUpdateByPasswordUserNotFound() {
        String username = "user1";
        Login updatedUser = new Login(username, "newPassword", "User", 1234567890, "user1@example.com", "Country");

        Mockito.when(loginRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            service.updateByPassword(username, updatedUser);
        });
    }

}
