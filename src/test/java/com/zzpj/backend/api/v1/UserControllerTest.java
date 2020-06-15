package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.security.MyUserDetailsService;
import com.zzpj.backend.services.implementations.UserService;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import com.zzpj.backend.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_whenValidInput_thenReturnsUsersList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users")).andReturn();

        ArrayList<User> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUser_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/users/"  + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUser_whenValidInput_thenReturnsUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/"  + UUID.randomUUID())).andReturn();

        User expectedResponseBody = new User();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void edit_whenValidInput_thenReturns200 () throws Exception {
        UserDTO user = new UserDTO();
        user.setLogin("Miro");
        user.setPassword("kfshdkfsdjgbsjbgjb5r43y52tr673476");

        Mockito.doNothing().when(userService).editUser(any(User.class));

        mockMvc.perform(put("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void delete_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + UUID.randomUUID())).
                andExpect(status().isOk());
    }
}
