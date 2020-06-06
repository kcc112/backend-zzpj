package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.WarehouseDTO;
import com.zzpj.backend.entities.Warehouse;

public class WarehouseMapper {

    public static Warehouse mapWarehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setUuid(warehouseDTO.getUuid());
        warehouse.setAmount(warehouseDTO.getAmount());

        return warehouse;
    }
}
