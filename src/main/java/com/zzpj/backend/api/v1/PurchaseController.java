package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchases")
public class PurchaseController {

    private PurchaseServiceLocal purchaseService;

    @Autowired
    public PurchaseController(PurchaseServiceLocal purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> get(){
        return purchaseService.getAllPurchases();
    }

    @PostMapping()
    public String add(@RequestBody Purchase purchase){
        purchaseService.addPurchase(purchase);
        return "Success";
    }
}
