package com.zzpj.backend.services.interfaceses;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.exceptions.AppBaseException;

import java.util.List;

public interface PurchaseServiceLocal {

    List<Purchase> getAllPurchases();
    void addPurchase(AddPurchaseDTO purchase) throws AppBaseException;
    public List<Purchase> getAllUserPurchases(String  login);
}
