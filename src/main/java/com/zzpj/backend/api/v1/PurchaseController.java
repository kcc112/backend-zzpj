package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    private PurchaseServiceLocal purchaseService;

    @Autowired
    public PurchaseController(PurchaseServiceLocal purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> get() {
        return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Purchase purchase) {
      try {
          if (purchase.getUuid() != null) throw new AppBaseException();
          purchaseService.addPurchase(purchase);
      } catch (AppBaseException e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
