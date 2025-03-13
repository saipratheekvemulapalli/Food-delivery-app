package com.fds.registration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fds.registration.model.Registration;
import com.fds.registration.service.RegistrationServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void testSaveUserWithValidRole() throws Exception {
        Registration user = new Registration();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole("User");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setCountry("TestCountry");

        Mockito.when(registrationService.save(user)).thenReturn(user);

        mockMvc.perform(post("/register/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveUserWithInvalidRole() throws Exception {
        Registration user = new Registration();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole("InvalidRole");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setCountry("TestCountry");

        mockMvc.perform(post("/register/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isInternalServerError());
    }
}
