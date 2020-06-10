package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.GetAllPurchaseDTO;
import com.zzpj.backend.dto.UserLoginIdDTO;
import com.zzpj.backend.entities.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseGetAllMapper {

    private PurchaseGetAllMapper() {}

    public static GetAllPurchaseDTO mapPurchaseToGetAllPurchaseDTO(Purchase purchase) {
        GetAllPurchaseDTO purchaseDTO = new GetAllPurchaseDTO(purchase.getUuid());
        purchaseDTO.setPurchaseLists(purchase.getPurchaseLists());
        UserLoginIdDTO userDTO = new UserLoginIdDTO(purchase.getUser().getUuid());
        userDTO.setLogin(purchase.getUser().getLogin());
        purchaseDTO.setUser(userDTO);
        return purchaseDTO;
    }

    public static List<GetAllPurchaseDTO> getAllPurchaseDTO(List<Purchase> purchases) {
        List<GetAllPurchaseDTO> purchasesDTO = new ArrayList<>();
        for(Purchase purchase: purchases) {
            purchasesDTO.add(mapPurchaseToGetAllPurchaseDTO(purchase));
        }
        return purchasesDTO;
    }
}
