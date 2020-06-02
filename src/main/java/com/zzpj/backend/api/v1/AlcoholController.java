package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Alcohol;
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
    public List<Alcohol> getAll() {
        return alcoholService.getAllAlcohols();
    }

    @GetMapping("{id}")
    public Alcohol get(@PathVariable Long id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        return alcohol.orElseGet(Alcohol::new);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Alcohol alcohol) {
        try {
            if(alcohol.getName() == null) throw  new Exception();
            alcoholService.addAlcohol(alcohol);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Alcohol alcohol) {
        try {
            if(alcohol.getId() != null && alcohol.getId() < 0) throw new Exception();
            alcoholService.editAlcohol(alcohol);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            if(id < 0) throw new Exception();
            alcoholService.deleteAlcohol(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
