package com.zzpj.backend.services;

import com.zzpj.backend.entities.Alcohol;

import java.util.List;
import java.util.UUID;

public interface AlcoholServiceLocal {

     void addAlcohol(Alcohol alcohol);
     Alcohol getAlcohol(UUID id);
     List<Alcohol> getAllAlcohols();
     void deleteAlcohol(UUID id);
     void editAlcohol(Alcohol alcohol);
}
