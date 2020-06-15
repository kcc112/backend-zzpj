package com.zzpj.backend.services.implementations;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.dto.AddPurchaseListDTO;
import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.entities.PurchaseList;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.repositories.AlcoholRepository;
import com.zzpj.backend.repositories.PurchaseRepository;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements PurchaseServiceLocal {

   private PurchaseRepository purchaseRepository;
   private AlcoholRepository alcoholRepository;
   private UserRepository userRepository;

   @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, AlcoholRepository alcoholRepository, UserRepository userRepository) {
       this.purchaseRepository = purchaseRepository;
       this.alcoholRepository = alcoholRepository;
       this.userRepository = userRepository;
   }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public void addPurchase(AddPurchaseDTO purchaseDTO) throws AppBaseException {
       Purchase purchase = new Purchase();
       Optional<User> user = userRepository.findById(purchaseDTO.getUserID());
       if(user.isPresent()){
           purchase.setUser(user.get());
       }
       else {
           throw UserException.exceptionForUserNotFound();
        }
       if(purchaseDTO.getPurchaseList().isEmpty()){
           throw AlcoholException.exceptionForAlcoholNotFound();
       }
        for (AddPurchaseListDTO purchaseList : purchaseDTO.getPurchaseList()) {
            Optional<Alcohol> alcohol = alcoholRepository.findById(purchaseList.getAlcoholID());
            if (alcohol.isPresent() && purchaseList.getBuyAmount() <= alcohol.get().getWarehouse().getAmount()){
                alcohol.get().getWarehouse().setAmount(alcohol.get().getWarehouse().getAmount() - purchaseList.getBuyAmount());
                PurchaseList purchaseList1 = new PurchaseList();
                purchaseList1.setPurchase(purchase);
                purchaseList1.setBuyAmount(purchaseList.getBuyAmount());
                purchaseList1.setAlcohol(alcohol.get());
                purchase.getPurchaseLists().add(purchaseList1);
            }
            else if(!alcohol.isPresent()){
                throw AlcoholException.exceptionForAlcoholNotFound();
            }
            else {
                throw AlcoholException.exceptionToFewAlcohol();
            }
        }
        purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAllUserPurchases(String  login){
       return purchaseRepository.findAll()
               .stream()
               .filter(e -> e.getUser().getLogin().equals(login))
               .collect(Collectors.toList());
    }
}
