package com.zzpj.backend.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.UUID;

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
    AlcoholServiceLocal alcoholServiceLocal;

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
        mockMvc.perform(get("/api/v1/alcohols/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void getAlcohol_whenValidInput_thenReturnsAlcohol() throws Exception {
        Alcohol expectedResponseBody = new Alcohol();
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/alcohols/" + UUID.randomUUID())).andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void add_whenValidInput_thenReturns201 () throws Exception {
        AlcoholDTO alcohol = new AlcoholDTO();
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
        Warehouse warehouse = new Warehouse();
        warehouse.setAmount(20);
        alcohol.setWarehouse(warehouse);
        alcohol.setName(null);
        alcohol.setCost(2.5);
        mockMvc.perform(post("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isInternalServerError());
    }

    @Test
    void edit_whenValidInput_thenReturns200 () throws Exception {
        AlcoholDTO alcohol = new AlcoholDTO();
        alcohol.setName("Perla");
        alcohol.setCost(2.5);
        mockMvc.perform(put("/api/v1/alcohols")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alcohol))
        ).andExpect(status().isOk());
    }

    @Test
    void delete_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(delete("/api/v1/alcohols/" + UUID.randomUUID())).
                andExpect(status().isOk());
    }
}
