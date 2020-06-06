package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AlcoholService implements AlcoholServiceLocal {

   private AlcoholRepository alcoholRepository;

   @Autowired
    public AlcoholService(AlcoholRepository alcoholRepository) {
        this.alcoholRepository = alcoholRepository;
    }

    @Override
    public void addAlcohol(Alcohol alcohol) {
      Optional<Alcohol> alcoholDB = alcoholRepository.findAll()
              .stream()
              .filter(e -> e.getName().equals(alcohol.getName()))
              .findFirst();
      if (alcoholDB.isPresent()) {
          alcoholDB.get().getWarehouse().addAmount(alcohol.getWarehouse().getAmount());
          alcoholRepository.save(alcoholDB.get());
      } else {
          alcoholRepository.save(alcohol);
      }
    }

    @Override
    public Optional<Alcohol> getAlcohol(UUID id) {
        return alcoholRepository.findById(id);
    }

    @Override
    public List<Alcohol> getAllAlcohols() {
       return alcoholRepository.findAll();
    }

    @Override
    public void deleteAlcohol(UUID id) {
        alcoholRepository.deleteById(id);
    }

    @Override
    public void editAlcohol(Alcohol alcohol) {
       if (alcoholRepository.findById(alcohol.getUuid()).isPresent()) {
           alcoholRepository.save(alcohol);
       }
    }
}
