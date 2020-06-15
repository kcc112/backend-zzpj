package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AddAlcoholDTO;
import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.mappers.AlcoholMapper;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import com.zzpj.backend.services.interfaceses.WarehouseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/alcohols")
public class AlcoholController {

    private final AlcoholServiceLocal alcoholService;
    private final WarehouseServiceLocal warehouseService;

    @Autowired
    public AlcoholController(@Qualifier("alcoholServiceWithCurrencies")AlcoholServiceLocal alcoholService, WarehouseServiceLocal warehouseService) {
        this.alcoholService = alcoholService;
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Alcohol>> getAll() {
        List<Alcohol> alcohols = alcoholService.getAllAlcohols();
        return new ResponseEntity<>(alcohols, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Alcohol> get(@PathVariable UUID id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        return new ResponseEntity<>(alcohol.orElseGet(Alcohol::new), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddAlcoholDTO addAlcoholDTO) {
        Alcohol alcohol = AlcoholMapper.mapAddAlcoholDTOToAlcohol(addAlcoholDTO);
        try {
            if (alcohol.getName() == null) throw new AppBaseException("Invalid data");
            Optional<Warehouse> warehouseFromDB = warehouseService.getWarehouse(addAlcoholDTO.getWarehouseUuid());
            if (!warehouseFromDB.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            alcohol.setWarehouse(warehouseFromDB.get());
            alcoholService.addAlcohol(alcohol);
        } catch (AlcoholException e) {
          if (e.getMessage().contains(AlcoholException.ALCOHOL_WITH_GIVEN_NAME_EXIST)) {
              return new ResponseEntity<>(HttpStatus.CONFLICT);
          }
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody AlcoholDTO alcoholDTO) {
        Alcohol alcohol = AlcoholMapper.mapAlcoholDTOToAlcohol(alcoholDTO);
        try {
            if (alcohol.getUuid() != null) throw new AppBaseException("Invalid data");
            alcoholService.editAlcohol(alcohol);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            alcoholService.deleteAlcohol(id);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
