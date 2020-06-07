package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.dto.GetAllPurchaseDTO;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.exceptions.AlcoholException;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.mappers.PurchaseGetAllMapper;
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
    public ResponseEntity<List<GetAllPurchaseDTO>> getAll() {
        return new ResponseEntity<>(PurchaseGetAllMapper.getAllPurchaseDTO(purchaseService.getAllPurchases()), HttpStatus.OK);
    }

    //TODO prawdopodobnie login będzie wyciągany w inny sposób
    @GetMapping("user/{login}")
    public ResponseEntity<List<GetAllPurchaseDTO>> getAllThisUser(@PathVariable String login) {
        return new ResponseEntity<>(PurchaseGetAllMapper.getAllPurchaseDTO(purchaseService.getAllUserPurchases(login)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddPurchaseDTO purchase) {
      try {
          purchaseService.addPurchase(purchase);
      }
      catch (AppBaseException e) {
          if(e.getMessage().equals(AlcoholException.TOO_FEW_ALCOHOL)){
              return new ResponseEntity<>("Too few alcohol in warehouse", HttpStatus.FOUND);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
