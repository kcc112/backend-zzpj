package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.dto.GetAllPurchaseDTO;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.mappers.PurchaseGetAllMapper;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
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
    private final CurrencyServiceLocal currencyService;

    @Autowired
    public PurchaseController(PurchaseServiceLocal purchaseService, CurrencyServiceLocal currencyService) {
        this.purchaseService = purchaseService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllPurchaseDTO>> getAll() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        purchases.forEach(
                x -> x.getPurchaseLists()
                        .forEach(y -> y.getAlcohol()
                            .setCost(currencyService.convertCurrencies(y.getAlcohol().getCost(), "USD", "PLN"))));
        return new ResponseEntity<>(PurchaseGetAllMapper.getAllPurchaseDTO(purchases), HttpStatus.OK);
    }

    @GetMapping("user/{login}")
    public ResponseEntity<List<GetAllPurchaseDTO>> getAllThisUser(@PathVariable String login) {
        List<Purchase> purchases = purchaseService.getAllUserPurchases(login);
        purchases.forEach(
                x -> x.getPurchaseLists()
                        .forEach(y -> y.getAlcohol()
                                .setCost(currencyService.convertCurrencies(y.getAlcohol().getCost(), "USD", "PLN"))));
        return new ResponseEntity<>(PurchaseGetAllMapper.getAllPurchaseDTO(purchases), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddPurchaseDTO purchase) {
      try {
          purchaseService.addPurchase(purchase);
      } catch (AppBaseException e) {
          if (e.getMessage().equals(AlcoholException.TOO_FEW_ALCOHOL)) {
              return new ResponseEntity<>("Too few alcohol in warehouse", HttpStatus.FOUND);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
