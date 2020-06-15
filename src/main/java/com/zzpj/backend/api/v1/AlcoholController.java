package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AddAlcoholDTO;
import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.mappers.AlcoholMapper;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
import com.zzpj.backend.services.interfaceses.WarehouseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CurrencyServiceLocal currencyService;

    @Autowired
    public AlcoholController(AlcoholServiceLocal alcoholService,
                             WarehouseServiceLocal warehouseService, CurrencyServiceLocal currencyService) {
        this.alcoholService = alcoholService;
        this.warehouseService = warehouseService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Alcohol>> getAll() {
        List<Alcohol> alcohols = alcoholService.getAllAlcohols();
        alcohols.forEach(x -> x.setCost(currencyService.convertCurrencies(x.getCost(),"USD", "PLN")));
        return new ResponseEntity<>(alcohols, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Alcohol> get(@PathVariable UUID id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        alcohol.ifPresent(x -> x.setCost(currencyService.convertCurrencies(x.getCost(), "USD", "PLN")));
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
            alcohol.setCost(currencyService.convertCurrencies(alcohol.getCost(), "PLN", "USD"));
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
            alcohol.setCost(currencyService.convertCurrencies(alcohol.getCost(), "PLN", "USD"));
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
