package com.zzpj.backend.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.WarehouseException;
import com.zzpj.backend.security.MyUserDetailsService;
import com.zzpj.backend.services.implementations.WarehouseService;
import com.zzpj.backend.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WarehouseController.class)
class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WarehouseService warehouseService;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    @WithMockUser(roles = "USER")
    void addWarehouse_thenReturns201() throws Exception {
        UUID uuid = UUID.randomUUID();

        mockMvc.perform(post("/api/v1/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"" + uuid + "\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void addWarehouse_withExistedUuid_thenReturns409() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.doThrow(WarehouseException.createWarehouseAlreadyExistException()).when(warehouseService).addWarehouse(any(Warehouse.class));

        mockMvc.perform(post("/api/v1/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"" + uuid + "\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "USER")
    void addWarehouse_invalidRequest_thenReturns400() throws Exception {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        mockMvc.perform(post("/api/v1/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(warehouse1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getWarehouse_thenReturns200() throws Exception {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        Mockito.when(warehouseService.getWarehouse(any(UUID.class))).thenReturn(Optional.of(warehouse1));

        mockMvc.perform(get("/api/v1/warehouses/" + uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"" + uuid + "\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getWarehouse_withNotExistedUuid_thenReturn404() throws Exception {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        Mockito.when(warehouseService.getWarehouse(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/warehouses/" + uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"" + uuid + "\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllWarehouses_thenReturn200() throws Exception {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        mockMvc.perform(get("/api/v1/warehouses/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
