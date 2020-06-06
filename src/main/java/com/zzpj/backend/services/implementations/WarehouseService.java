package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.WarehouseException;
import com.zzpj.backend.repositories.WarehouseRepository;
import com.zzpj.backend.services.interfaceses.WarehouseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService implements WarehouseServiceLocal {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void addWarehouse(Warehouse warehouse) throws AppBaseException {
        Optional<Warehouse> warehouseFromDB = warehouseRepository.findById(warehouse.getUuid());
        if (warehouseFromDB.isPresent()) {
            throw WarehouseException.createWarehouseAlreadyExistException();
        } else {
            warehouseRepository.save(warehouse);
        }
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseRepository.delete(warehouse);
    }

    @Override
    public Optional<Warehouse> getWarehouse(UUID uuid) {
        return warehouseRepository.findById(uuid);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}
