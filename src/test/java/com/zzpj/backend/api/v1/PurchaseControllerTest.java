package com.zzpj.backend.api.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.entities.PurchaseList;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
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

@WebMvcTest(controllers = PurchaseController.class)
public class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PurchaseServiceLocal purchaseService;


    @Test
    void getAll_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/purchases"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_whenValidInput_thenReturnsPurchaseList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/purchases")).andReturn();

        ArrayList<Alcohol> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void add_whenValidInput_thenReturns200 () throws Exception {
        Purchase purchase = new Purchase();
        purchase.setUser(new User());
        purchase.setPurchaseLists(new ArrayList<>());
        mockMvc.perform(post("/api/v1/purchases")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(purchase))
        ).andExpect(status().isOk());
    }

}
