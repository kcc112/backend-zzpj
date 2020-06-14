package com.zzpj.backend.services.interfaceses;

import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AppBaseException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseServiceLocal {

    void addWarehouse(Warehouse warehouse) throws AppBaseException;
    void deleteWarehouse(Warehouse warehouse);
    Optional<Warehouse> getWarehouse(UUID uuid);
    List<Warehouse> getAllWarehouses();
}
