package com.zzpj.backend.init;

import com.zzpj.backend.entities.*;
import com.zzpj.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {

    private AlcoholRepository alcoholRepository;
    private PurchaseListRepository purchaseListRepository;
    private PurchaseRepository purchaseRepository;
    private UserRepository userRepository;
    private WarehouseRepository warehouseRepository;

    public DBInit(AlcoholRepository alcoholRepository, PurchaseListRepository purchaseListRepository, PurchaseRepository purchaseRepository, UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.alcoholRepository = alcoholRepository;
        this.purchaseListRepository = purchaseListRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        Warehouse warehouse = new Warehouse();
        warehouse.setAmount(10);
        warehouseRepository.save(warehouse);

        Alcohol alcohol = new Alcohol();
        List<Alcohol> alcohols = new ArrayList<>();
        alcohols.add(alcohol);
        alcohol.setName("Tatra");
        alcohol.setCost(2);
        alcohol.setWarehouse(warehouse);
        warehouse.setAlcohols(alcohols);
        alcoholRepository.save(alcohol);

        User user = new User();
        user.setLogin("Szymon");
        user.setPassword("kfshdkfsdjgbsjbgjb5r43y52tr673476");
        user.setType("ADMIN");
        userRepository.save(user);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchaseRepository.save(purchase);

        PurchaseList purchaseList = new PurchaseList();
        purchaseList.setAlcohol(alcohol);
        purchaseList.setPurchase(purchase);
        purchaseList.setBuyAmount(33);
        purchaseListRepository.save(purchaseList);
    }
}