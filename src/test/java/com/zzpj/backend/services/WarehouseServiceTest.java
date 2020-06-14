package com.zzpj.backend.services;

import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.WarehouseException;
import com.zzpj.backend.repositories.WarehouseRepository;
import com.zzpj.backend.services.implementations.WarehouseService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseService warehouseService;

    @Test
    public void addServiceUuidExistTest() {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse = new Warehouse();
        warehouse.setUuid(uuid);
        warehouse.setAmount(20);

        Mockito.when(warehouseRepository.findById(warehouse.getUuid())).thenReturn(Optional.of(warehouse));

        try {
            warehouseService.addWarehouse(warehouse);
        } catch (AppBaseException e) {
            Assertions.assertEquals(WarehouseException.WAREHOUSE_ALREADY_EXIST, e.getMessage());
        }

        Mockito.verify(warehouseRepository, Mockito.times(0)).save(warehouse);
    }

    @Test
    public void addWarehouseUuidNotExistTest() {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        Mockito.when(warehouseRepository.findById(warehouse1.getUuid())).thenReturn(Optional.empty());

        try {
            warehouseService.addWarehouse(warehouse1);
        } catch (AppBaseException ignore) {
        }

        Mockito.verify(warehouseRepository, Mockito.times(1)).save(warehouse1);
    }

    @Test
    public void deleteWarehouseTest() {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        Mockito.doNothing().when(warehouseRepository).delete(warehouse1);

        warehouseService.deleteWarehouse(warehouse1);

        Mockito.verify(warehouseRepository, Mockito.times(1)).delete(warehouse1);
    }

    @Test
    public void getWarehouseTest() {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setUuid(uuid);
        warehouse1.setAmount(20);

        Mockito.when(warehouseRepository.findById(uuid)).thenReturn(Optional.of(warehouse1));

        Optional<Warehouse> warehouseFromService = warehouseService.getWarehouse(uuid);

        Assertions.assertTrue(warehouseFromService.isPresent());

        Mockito.verify(warehouseRepository, Mockito.times(1)).findById(uuid);
        Assertions.assertEquals(warehouse1.getAmount(), warehouseFromService.get().getAmount());
    }

    @Test
    public void getAllWarehousesTest() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();
        warehouse1.setUuid(uuid1);
        warehouse2.setUuid(uuid2);
        warehouse1.setAmount(20);
        warehouse2.setAmount(30);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);

        Mockito.when(warehouseRepository.findAll()).thenReturn(warehouses);

        List<Warehouse> warehousesFromService = warehouseService.getAllWarehouses();

        Mockito.verify(warehouseRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(warehouses, warehousesFromService);
    }
}
