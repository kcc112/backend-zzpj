package com.zzpj.backend.services;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.services.implementations.AlcoholService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class AlcoholServiceTest {

    @Mock
    private AlcoholRepository alcoholRepository;

    @InjectMocks
    private AlcoholService alcoholService;

    @Test
    public void testAddAlcohol() throws AppBaseException {
        UUID uuid = UUID.randomUUID();
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setCost(25.40);
        alcohol1.setUuid(uuid);
        alcohol1.setName("Żubrówka");

        Mockito.when(alcoholRepository.save(alcohol1)).thenReturn(alcohol1);

        alcoholService.addAlcohol(alcohol1);

        Mockito.verify(alcoholRepository, Mockito.times(1)).save(alcohol1);
    }

    @Test
    public void testGetAlcohol() {
        UUID uuid = UUID.randomUUID();
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setCost(25.40);
        alcohol1.setUuid(uuid);
        alcohol1.setName("Żubrówka");

        Mockito.when(alcoholRepository.findById(uuid)).thenReturn(Optional.of(alcohol1));

        Optional<Alcohol> alcoholFromService = alcoholService.getAlcohol(uuid);

        Assertions.assertEquals(Optional.of(alcohol1), alcoholFromService);
        Assertions.assertEquals(alcohol1.getName(), alcoholFromService.get().getName());
        Mockito.verify(alcoholRepository, Mockito.times(1)).findById(uuid);
    }

    @Test
    public void testGetAllAlcohols() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setCost(25.40);
        alcohol1.setUuid(uuid1);
        alcohol1.setName("Żubrówka");
        Alcohol alcohol2 = new Alcohol();
        alcohol1.setCost(55.20);
        alcohol1.setUuid(uuid2);
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
        UUID uuid = UUID.randomUUID();

        alcoholService.deleteAlcohol(uuid);

        Mockito.verify(alcoholRepository, Mockito.times(1)).deleteById(uuid);
    }

    @Test
    public void testEditAlcohol() {
        UUID uuid1 = UUID.randomUUID();
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setCost(25.40);
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setAmount(20);
        alcohol1.setWarehouse(warehouse1);
        alcohol1.setUuid(uuid1);
        alcohol1.setName("Żubrówka");
        Alcohol alcohol2 = new Alcohol();
        Warehouse warehouse2 = new Warehouse();
        warehouse2.setAmount(30);
        alcohol2.setWarehouse(warehouse2);
        alcohol2.setCost(55.50);
        alcohol2.setUuid(uuid1);
        alcohol2.setName("Tyskie");

        Mockito.when(alcoholRepository.findById(uuid1)).thenReturn(Optional.of(alcohol1));
        Mockito.when(alcoholRepository.save(alcohol2)).thenReturn(alcohol2);

        alcoholService.editAlcohol(alcohol2);

        Mockito.verify(alcoholRepository, Mockito.times(1)).save(alcohol2);
        Mockito.verify(alcoholRepository, Mockito.times(1)).findById(uuid1);
    }
}
