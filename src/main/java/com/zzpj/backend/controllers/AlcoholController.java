package com.zzpj.backend.controllers;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.services.AlcoholServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alcohols")
public class AlcoholController {

    private AlcoholServiceLocal alcoholService;

    @Autowired
    public AlcoholController(AlcoholServiceLocal alcoholService) {
        this.alcoholService = alcoholService;
    }

    @GetMapping
    public List<Alcohol> getAll(){
        return alcoholService.getAllAlcohols();
    }

    @GetMapping("{id}")
    public Alcohol get(@PathVariable Long id){
        return alcoholService.getAlcohol(id);
    }

    @PostMapping
    public String add(@RequestBody Alcohol alcohol){
        alcoholService.addAlcohol(alcohol);
        return "Success";
    }

    @PutMapping
    public String edit(@RequestBody Alcohol alcohol){
        alcoholService.editAlcohol(alcohol);
        return "Success";
    }

    @DeleteMapping("{id}")
    public String del(@PathVariable Long id){
        alcoholService.deleteAlcohol(id);
        return "Success";
    }


}
