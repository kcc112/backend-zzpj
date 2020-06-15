package com.zzpj.backend.services.interfaceses;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.exceptions.AppBaseException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AlcoholServiceLocal {

     void addAlcohol(Alcohol alcohol) throws AppBaseException;
     Optional<Alcohol> getAlcohol(UUID id);
     List<Alcohol> getAllAlcohols();
     void deleteAlcohol(UUID id) throws AppBaseException;
     void editAlcohol(Alcohol alcohol) throws AppBaseException;
}
