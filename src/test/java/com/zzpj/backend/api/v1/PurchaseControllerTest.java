package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.security.MyUserDetailsService;
import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import com.zzpj.backend.utils.JwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PurchaseController.class)
class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Qualifier("purchaseServiceWithCurrencies")
    @MockBean
    PurchaseServiceLocal purchaseService;

    @MockBean
    CurrencyServiceLocal currencyServiceLocal;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    @WithMockUser(roles = "USER")
    void getAll_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/purchases"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllForUser_whenValidInput_thenReturns200 () throws Exception {
        mockMvc.perform(get("/api/v1/purchases/user/Szymon"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAll_whenValidInput_thenReturnsPurchaseList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/purchases")).andReturn();

        ArrayList<Alcohol> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllForUser_whenValidInput_thenReturnsPurchaseList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/purchases/user/Szymon")).andReturn();

        ArrayList<Alcohol> expectedResponseBody = new ArrayList<>();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    @WithMockUser(roles = "USER")
    void add_whenValidInput_thenReturns201 () throws Exception {
        Purchase purchase = new Purchase();
        purchase.setUser(new User());
        purchase.setPurchaseLists(new ArrayList<>());
        mockMvc.perform(post("/api/v1/purchases")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(purchase))
        ).andExpect(status().isCreated());
    }
}
