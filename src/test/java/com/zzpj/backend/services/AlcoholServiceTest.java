package com.zzpj.backend.services;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.services.implementations.AlcoholService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AlcoholServiceTest {

    @Mock
    private AlcoholRepository alcoholRepository;

    @InjectMocks
    private AlcoholService alcoholService;

    @Test
    public void testAddAlcohol() {
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setAmount(100);
        alcohol1.setCost(25.40);
        alcohol1.setId(1L);
        alcohol1.setName("Żubrówka");

        Mockito.when(alcoholRepository.save(alcohol1)).thenReturn(alcohol1);

        alcoholService.addAlcohol(alcohol1);

        Mockito.verify(alcoholRepository, Mockito.times(1)).save(alcohol1);
    }

    @Test
    public void testGetAlcohol() {
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setAmount(100);
        alcohol1.setCost(25.40);
        alcohol1.setId(1L);
        alcohol1.setName("Żubrówka");

        Mockito.when(alcoholRepository.findById(1L)).thenReturn(Optional.of(alcohol1));

        Optional<Alcohol> alcoholFromService = alcoholService.getAlcohol(1L);

        Assertions.assertEquals(Optional.of(alcohol1), alcoholFromService);
        Assertions.assertEquals(alcohol1.getName(), alcoholFromService.get().getName());
        Mockito.verify(alcoholRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testGetAllAlcohols() {
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setAmount(100);
        alcohol1.setCost(25.40);
        alcohol1.setId(1L);
        alcohol1.setName("Żubrówka");
        Alcohol alcohol2 = new Alcohol();
        alcohol1.setAmount(200);
        alcohol1.setCost(55.20);
        alcohol1.setId(2L);
        alcohol1.setName("Tyskie");
        List<Alcohol> alcohols = new ArrayList<>();
        alcohols.add(alcohol1);
        alcohols.add(alcohol2);

        Mockito.when(alcoholRepository.findAll()).thenReturn(alcohols);

        List<Alcohol> alcoholsFromService = alcoholService.getAllAlcohols();

        Assertions.assertEquals(alcoholsFromService, alcohols);
        Assertions.assertEquals(alcohols.get(0).getName(), alcoholsFromService.get(0).getName());
        Mockito.verify(alcoholRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteAlcohol() {
        Mockito.doNothing().when(alcoholRepository).deleteById(1L);

        alcoholService.deleteAlcohol(1L);

        Mockito.verify(alcoholRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testEditAlcohol() {
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setAmount(100);
        alcohol1.setCost(25.40);
        alcohol1.setId(1L);
        alcohol1.setName("Żubrówka");
        Alcohol alcohol2 = new Alcohol();
        alcohol2.setAmount(200);
        alcohol2.setCost(55.50);
        alcohol2.setId(1L);
        alcohol2.setName("Tyskie");

        Mockito.when(alcoholRepository.findById(1L)).thenReturn(Optional.of(alcohol1));
        Mockito.when(alcoholRepository.save(alcohol2)).thenReturn(alcohol2);

        alcoholService.editAlcohol(alcohol2);

        Mockito.verify(alcoholRepository, Mockito.times(1)).save(alcohol2);
        Mockito.verify(alcoholRepository, Mockito.times(1)).findById(1L);
    }
}
