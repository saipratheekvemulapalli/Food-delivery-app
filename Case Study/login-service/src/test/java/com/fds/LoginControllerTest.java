package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fds.request.LoginRequest;

@SpringBootTest
public class LoginControllerTest {



    @Test
    public void testValidateUser() throws Exception {
  

        LoginRequest loginRequest = new LoginRequest("testUser", "testPassword");

        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

       assertEquals(loginRequest.getUsername(),"testUser");
    }
}
