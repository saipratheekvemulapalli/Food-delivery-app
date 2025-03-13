package com.fds.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.registration.model.Registration;
import com.fds.registration.repository.RegistrationRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RegistrationRepoTest {

    @Autowired
    private RegistrationRepo registrationRepo;

    @Test
    public void testFindByUsername() {
        // Create a sample Registration object and save it to the database
        Registration sampleRegistration = new Registration();
        sampleRegistration.setUsername("testuser");
        sampleRegistration.setPassword("testpassword");
        sampleRegistration.setRole("user");
        sampleRegistration.setEmail("testuser@example.com");
        sampleRegistration.setPhoneNumber("1234567890");
        sampleRegistration.setCountry("Test Country");
        registrationRepo.save(sampleRegistration);

        // Find the Registration by username
        Registration foundRegistration = registrationRepo.findByUsername("testuser");

        // Assert that the foundRegistration is not null and has the expected username
        assertNotNull(foundRegistration);
        assertEquals("testuser", foundRegistration.getUsername());
    }
}
