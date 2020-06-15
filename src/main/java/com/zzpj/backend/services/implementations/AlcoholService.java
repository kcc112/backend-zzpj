package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
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
    public void addAlcohol(Alcohol alcohol) throws AppBaseException {
      Optional<Alcohol> alcoholDB = alcoholRepository.findAll()
              .stream()
              .filter(e -> e.getName().equals(alcohol.getName()))
              .findFirst();
      if (alcoholDB.isPresent()) {
          throw AlcoholException.createAlcoholWithGivenNameExistException();
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
