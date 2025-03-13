//package com.fds.registration;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.fds.registration.exception.RegistrationException;
//import com.fds.registration.model.Registration;
//import com.fds.registration.repository.RegistrationRepo;
//import com.fds.registration.service.RegistrationServiceImpl;
//
//public class RegistrationServiceImplTest {
//
//    @InjectMocks
//    private RegistrationServiceImpl registrationService;
//
//    @Mock
//    private RegistrationRepo registrationRepo;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveUser() {
//        Registration newUser = new Registration();
//        newUser.setUsername("testuser");
//        newUser.setPassword("testpassword");
//        newUser.setRole("ROLE_USER");
//        newUser.setEmail("testuser@example.com");
//        newUser.setPhoneNumber("1234567890");
//        newUser.setCountry("TestCountry");
//
//        // Mock the behavior of the RegistrationRepo
//        when(registrationRepo.findByUsername(newUser.getUsername())).thenReturn(null);
//        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("encodedPassword");
//        when(registrationRepo.save(newUser)).thenReturn(newUser);
//
//        Registration savedUser = registrationService.save(newUser);
//
//        assertNull(savedUser);
//
//    }
//
//    @Test
//    public void testSaveUserWithExistingUsername() {
//        Registration existingUser = new Registration();
//        existingUser.setUsername("existinguser");
//        when(registrationRepo.findByUsername(existingUser.getUsername())).thenReturn(existingUser);
//
//        Registration newUser = new Registration();
//        newUser.setUsername("existinguser");
//
//        try {
//            assertThrows(RegistrationException.class, () -> registrationService.save(newUser));
//        } catch (RegistrationException e) {
//            assertEquals("Username already exists: existinguser", e.getMessage());
//        }
//    }
//}
