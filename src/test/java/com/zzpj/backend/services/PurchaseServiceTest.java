package com.zzpj.backend.services;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.dto.AddPurchaseListDTO;
import com.zzpj.backend.entities.*;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.repositories.PurchaseListRepository;
import com.zzpj.backend.repositories.PurchaseRepository;
import com.zzpj.backend.repositories.UserRepository;
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
    private UserRepository userRepository;
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
    public void getAllUserPurchases() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        User user1 = new User();
        user1.setUuid(uuid1);
        user1.setLogin("test1");
        user1.setPassword("123");
        User user2 = new User();
        user2.setUuid(uuid1);
        user2.setLogin("test2");
        user2.setPassword("123");
        Purchase purchase1 = new Purchase();
        purchase1.setUuid(uuid1);
        purchase1.setUser(user1);
        Purchase purchase2 = new Purchase();
        purchase2.setUuid(uuid2);
        purchase2.setUser(user2);
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        Mockito.when(purchaseRepository.findAll()).thenReturn(purchases);

        List<Purchase> purchasesFromService = purchaseService.getAllUserPurchases("test1");

        Mockito.verify(purchaseRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(1, purchasesFromService.size());
        purchases.remove(purchase2);
        Assertions.assertEquals(purchases, purchasesFromService);

    }

    @Test
    public void addPurchase() {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        User user1 = new User();
        user1.setUuid(uuid);
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setAmount(100);
        Alcohol alcohol1 = new Alcohol();
        alcohol1.setUuid(uuid);
        alcohol1.setWarehouse(warehouse1);
        AddPurchaseDTO purchase1 = new AddPurchaseDTO();
        purchase1.setUserID(uuid);
        AddPurchaseListDTO purchaseList1 = new AddPurchaseListDTO();
        purchaseList1.setAlcoholID(uuid);
        purchaseList1.setBuyAmount(50);
        purchase1.getPurchaseList().add(purchaseList1);
        purchase1.setUserID(uuid);
        Purchase purchase = new Purchase();
        purchase.setUser(user1);
        purchase.setUuid(uuid);
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.setAlcohol(alcohol1);
        purchaseList.setBuyAmount(50);
        purchaseList.setPurchase(purchase);
        List<PurchaseList> purchaseLists = new ArrayList<>();
        purchaseLists.add(purchaseList);
        purchase.setPurchaseLists(purchaseLists);


        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user1));
        Mockito.when(alcoholRepository.findById(uuid)).thenReturn(Optional.of(alcohol1));

        try {
            purchaseService.addPurchase(purchase1);
        } catch (AppBaseException ignored) { }
        Assertions.assertEquals(50, alcohol1.getWarehouse().getAmount());
    }

}
