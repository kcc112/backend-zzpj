package com.zzpj.backend.services.interfaceses;

import com.zzpj.backend.entities.Alcohol;

import java.util.List;
import java.util.Optional;


public interface AlcoholServiceLocal {

     void addAlcohol(Alcohol alcohol);
     Optional<Alcohol> getAlcohol(Long id);
     List<Alcohol> getAllAlcohols();
     void deleteAlcohol(Long id);
     void editAlcohol(Alcohol alcohol);
}
