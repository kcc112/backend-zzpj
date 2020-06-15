package com.zzpj.backend.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.dto.AuthenticationUserDTO;
import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.security.MyUserDetailsService;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import com.zzpj.backend.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserServiceLocal userService;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    AuthenticationManager authenticationManager;

    @Test
    void registerAccount_whenValidInput_thenReturn200() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test@wp.pl");
        userDTO.setPassword("test1234");
        userDTO.setFirstName("Jan");
        userDTO.setLastName("Kowalski");
        userDTO.setMatchingPassword("test1234");

        mockMvc.perform(post("/api/v1/authentication/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void registerAccount_whenInvalidInput_thenReturn400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test");
        userDTO.setPassword("test1234");
        userDTO.setMatchingPassword("1234Test");

        mockMvc.perform(post("/api/v1/authentication/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAuthenticationToken_whenValidInput_thenReturn200() throws Exception {
        AuthenticationUserDTO authUserDTO = new AuthenticationUserDTO();
        authUserDTO.setLogin("Test@wp.pl");
        authUserDTO.setPassword("Test1234");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/authentication/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(authUserDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void createAuthenticationToken_whenInvalidInput_thenReturn400() throws Exception {
        AuthenticationUserDTO authUserDTO = new AuthenticationUserDTO();
        authUserDTO.setLogin("Test");

        mockMvc.perform(post("/api/v1/authentication/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(authUserDTO)))
                .andExpect(status().isBadRequest());

    }
}
