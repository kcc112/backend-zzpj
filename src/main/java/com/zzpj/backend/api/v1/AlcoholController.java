package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<Alcohol> get(@PathVariable Long id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        return new ResponseEntity<>(alcohol.orElseGet(Alcohol::new), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Alcohol alcohol) {
        try {
            if(alcohol.getName() == null) throw new AppBaseException();
            alcoholService.addAlcohol(alcohol);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody Alcohol alcohol) {
        try {
            if(alcohol.getId() != null && alcohol.getId() < 0) throw new AppBaseException();
            alcoholService.editAlcohol(alcohol);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            if(id < 0) throw new AppBaseException();
            alcoholService.deleteAlcohol(id);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
