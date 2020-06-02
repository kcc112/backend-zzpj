package com.zzpj.backend.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlcoholController.class)
public class AlcoholControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AlcoholServiceLocal alcoholService;


    @Test
    void getAll_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/alcohols"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_whenValidInput_thenReturnsAlcoholList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/alcohols")).andReturn();

        ArrayList<Alcohol> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void getAlcohol_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/alcohols/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAlcohol_whenValidInput_thenReturnsAlcohol() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/alcohols/1")).andReturn();

        Alcohol expectedResponseBody = new Alcohol();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void add_whenValidInput_thenReturns201 () throws Exception {
        Alcohol alcohol = new Alcohol();
        alcohol.setName("Perla");
        alcohol.setCost(2.5);
        mockMvc.perform(post("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isCreated());
    }

    @Test
    void add_whenInValidInput_thenReturns500 () throws Exception {
        Alcohol alcohol = new Alcohol();
        alcohol.setName(null);
        alcohol.setAmount(20);
        alcohol.setCost(2.5);
        mockMvc.perform(post("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isInternalServerError());
    }

    @Test
    void edit_whenValidInput_thenReturns200 () throws Exception {
        Alcohol alcohol = new Alcohol();
        alcohol.setName("Perla");
        alcohol.setCost(2.5);
        mockMvc.perform(put("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isOk());
    }

    @Test
    void edit_whenInValidInput_thenReturns304 () throws Exception {
        Alcohol alcohol = new Alcohol();
        alcohol.setId(-1l);
        mockMvc.perform(put("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isNotModified());
    }

    @Test
    void delete_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(delete("/api/v1/alcohols/1")).
                andExpect(status().isOk());
    }
    @Test
    void delete_whenInValidInput_thenReturns500 () throws Exception {
        mockMvc.perform(delete("/api/v1/alcohols/-1")).
                andExpect(status().isInternalServerError());
    }



}
