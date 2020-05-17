package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public Optional<Alcohol> getAlcohol(Long id) {
        return alcoholRepository.findById(id);
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
       if(alcoholRepository.findById(alcohol.getId()).isPresent()){
           alcoholRepository.save(alcohol);
       }
    }
}
