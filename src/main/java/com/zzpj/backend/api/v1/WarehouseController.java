package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.WarehouseDTO;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.WarehouseException;
import com.zzpj.backend.mappers.WarehouseMapper;
import com.zzpj.backend.services.interfaceses.WarehouseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private WarehouseServiceLocal warehouseService;

    @Autowired
    public WarehouseController(WarehouseServiceLocal warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Map<String, UUID> request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setUuid(request.get("uuid"));
        warehouse.setAmount(0);

        try {
            warehouseService.addWarehouse(warehouse);
        } catch (WarehouseException e) {
            if (e.getMessage().contains(WarehouseException.WAREHOUSE_ALREADY_EXIST)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Warehouse> get(@PathVariable UUID id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouse(id);

        return warehouse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll() {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.OK);
    }
}
