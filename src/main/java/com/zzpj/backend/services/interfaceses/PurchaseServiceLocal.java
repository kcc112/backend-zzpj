package com.zzpj.backend.services.interfaceses;

import com.zzpj.backend.entities.Purchase;

import java.util.List;

public interface PurchaseServiceLocal {

    List<Purchase> getAllPurchases();
    void addPurchase(Purchase purchase);
}
