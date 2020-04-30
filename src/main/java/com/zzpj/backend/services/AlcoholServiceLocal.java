package com.zzpj.backend.services;

import com.zzpj.backend.entities.Alcohol;

import java.util.List;


public interface AlcoholServiceLocal {

     void addAlcohol(Alcohol alcohol);
     Alcohol getAlcohol(Long id);
     List<Alcohol> getAllAlcohols();
     void deleteAlcohol(Long id);
     void editAlcohol(Alcohol alcohol);
}
