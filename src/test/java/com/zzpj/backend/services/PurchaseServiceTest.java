package com.zzpj.backend.services;

import com.zzpj.backend.entities.*;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.repositories.PurchaseListRepository;
import com.zzpj.backend.repositories.PurchaseRepository;
import com.zzpj.backend.services.implementations.PurchaseService;

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
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private PurchaseListRepository purchaseListRepository;
    @Mock
    private AlcoholRepository alcoholRepository;
    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void getAllPurchases() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        User user1 = new User();
        user1.setUuid(uuid1);
        user1.setLogin("test1");
        user1.setPassword("123");
        Purchase purchase1 = new Purchase();
        purchase1.setUuid(uuid1);
        purchase1.setUser(user1);
        Purchase purchase2 = new Purchase();
        purchase2.setUuid(uuid2);
        purchase2.setUser(user1);
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        Mockito.when(purchaseRepository.findAll()).thenReturn(purchases);

        List<Purchase> purchasesFromService = purchaseService.getAllPurchases();

        Mockito.verify(purchaseRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(purchases, purchasesFromService);
    }

    @Test
    public void addPurchase() {
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setAmount(100);
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setUuid(uuid);
        alcohol1.setWarehouse(warehouse1);
        Purchase purchase1 = new Purchase();
        purchase1.setUuid(uuid);
        PurchaseList purchaseList1 = new PurchaseList();
        purchaseList1.setPurchase(purchase1);
        purchaseList1.setUuid(uuid);
        purchaseList1.setBuyAmount(50);
        purchaseList1.setAlcohol(alcohol1);
        purchase1.getPurchaseLists().add(purchaseList1);

        Mockito.when(alcoholRepository.findById(uuid)).thenReturn(Optional.of(alcohol1));
        Mockito.when(purchaseRepository.save(purchase1)).thenReturn(purchase1);

        purchaseService.addPurchase(purchase1);

        Mockito.verify(alcoholRepository, Mockito.times(1)).findById(uuid);
        Mockito.verify(purchaseRepository, Mockito.times(1)).save(purchase1);
        Assertions.assertEquals(50, alcohol1.getWarehouse().getAmount());

    }
}
