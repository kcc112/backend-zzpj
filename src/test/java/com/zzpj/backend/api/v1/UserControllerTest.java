package com.zzpj.backend.api.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserServiceLocal userService;

    @Test
    void getAll_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_whenValidInput_thenReturnsUsersList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users")).andReturn();

        ArrayList<User> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void getUser_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getUser_whenValidInput_thenReturnsUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1")).andReturn();

        User expectedResponseBody = new User();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void add_whenValidInput_thenReturns200 () throws Exception {
        User user = new User();
        user.setLogin("Miro");
        user.setPassword("kfshdkfsdjgbsjbgjb5r43y52tr673476");
        user.setType("ADMIN");
        mockMvc.perform(post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }

    @Test
    void edit_whenValidInput_thenReturns200 () throws Exception {
        User user = new User();
        user.setLogin("Miro");
        user.setPassword("kfshdkfsdjgbsjbgjb5r43y52tr673476");
        user.setType("ADMIN");
        mockMvc.perform(put("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }

    @Test
    void delete_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(delete("/api/v1/users/1")).
                andExpect(status().isOk());
    }

}