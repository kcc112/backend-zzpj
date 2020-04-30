package com.zzpj.backend.services;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.repositories.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlcoholService implements AlcoholServiceLocal {

   private AlcoholRepository alcoholRepository;

   @Autowired
    public AlcoholService(AlcoholRepository alcoholRepository) {
        this.alcoholRepository = alcoholRepository;
    }

    @Override
    public void addAlcohol(Alcohol alcohol) {
        alcoholRepository.save(alcohol);
    }

    @Override
    public Alcohol getAlcohol(Long id) {
        return alcoholRepository.findById(id).get();
    }

    @Override
    public List<Alcohol> getAllAlcohols() {
        return alcoholRepository.findAll();
    }

    @Override
    public void deleteAlcohol(Long id) {
        alcoholRepository.deleteById(id);
    }

    @Override
    public void editAlcohol(Alcohol alcohol) {
       alcoholRepository.save(alcohol);
    }
}
