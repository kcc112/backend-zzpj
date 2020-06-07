package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.mappers.AlcoholMapper;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
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

    private AlcoholServiceLocal alcoholService;

    @Autowired
    public AlcoholController(AlcoholServiceLocal alcoholService) {
        this.alcoholService = alcoholService;
    }

    @GetMapping
    public ResponseEntity<List<Alcohol>> getAll() {
        return new ResponseEntity<>(alcoholService.getAllAlcohols(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Alcohol> get(@PathVariable UUID id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        return new ResponseEntity<>(alcohol.orElseGet(Alcohol::new), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AlcoholDTO alcoholDTO) {
        AlcoholMapper alcoholMapper = new AlcoholMapper();
        Alcohol alcohol = alcoholMapper.mapAlcoholDTOToAlcohol(alcoholDTO);
        try {
            if (alcohol.getName() == null) throw new AppBaseException("Invalid data");
            alcoholService.addAlcohol(alcohol);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody AlcoholDTO alcoholDTO) {
        AlcoholMapper alcoholMapper = new AlcoholMapper();
        Alcohol alcohol = alcoholMapper.mapAlcoholDTOToAlcohol(alcoholDTO);
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
