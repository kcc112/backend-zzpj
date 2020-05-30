package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpStatus add(@RequestBody Alcohol alcohol) {
        try {
            alcoholService.addAlcohol(alcohol);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.CREATED;
    }

    @PutMapping
    public HttpStatus edit(@RequestBody Alcohol alcohol) {
        try {
            alcoholService.editAlcohol(alcohol);
        } catch (Exception e) {
            return HttpStatus.NOT_MODIFIED;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try {
            alcoholService.deleteAlcohol(id);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }


}
