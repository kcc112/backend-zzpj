package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.entities.PurchaseList;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.repositories.PurchaseRepository;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements PurchaseServiceLocal {

   private PurchaseRepository purchaseRepository;
   private AlcoholRepository alcoholRepository;

   @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, AlcoholRepository alcoholRepository) {
       this.purchaseRepository = purchaseRepository;
       this.alcoholRepository = alcoholRepository;
   }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public void addPurchase(Purchase purchase) {
        for(PurchaseList purchaseList: purchase.getPurchaseLists()
            ){
            Optional<Alcohol> alcohol = alcoholRepository.findById(purchaseList.getAlcohol().getId());
            if(alcohol.isPresent() && purchaseList.getBuyAmount() <= alcohol.get().getAmount()){
                alcohol.get().setAmount(alcohol.get().getAmount() - purchaseList.getBuyAmount());
                purchaseList.setPurchase(purchase);
            }
        }
        purchaseRepository.save(purchase);
    }
}
