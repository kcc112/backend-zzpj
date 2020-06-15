package com.zzpj.backend.services.implementations;

import com.zzpj.backend.dto.AddPurchaseDTO;
import com.zzpj.backend.entities.Purchase;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
import com.zzpj.backend.services.interfaceses.PurchaseServiceLocal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("purchaseServiceWithCurrencies")
public class PurchaseServiceDecorator implements PurchaseServiceLocal {

    final private PurchaseServiceLocal purchaseService;
    final private CurrencyServiceLocal currencyService;

    @Value("${application.currency.base}")
    private String serverCurrency;

    @Value("${application.currency.local}")
    private String clientCurrency;

    public PurchaseServiceDecorator(PurchaseServiceLocal purchaseService, CurrencyServiceLocal currencyService) {
        this.purchaseService = purchaseService;
        this.currencyService = currencyService;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        purchases
                .forEach(purchase -> purchase.getPurchaseLists()
                        .forEach(purchaseList -> purchaseList.getAlcohol()
                                .setCost(currencyService.convertCurrencies(purchaseList.getAlcohol().getCost(), serverCurrency, clientCurrency))));
        return purchases;
    }

    @Override
    public void addPurchase(AddPurchaseDTO purchase) throws AppBaseException {
        purchaseService.addPurchase(purchase);
    }

    @Override
    public List<Purchase> getAllUserPurchases(String login) {
        List<Purchase> purchases = purchaseService.getAllUserPurchases(login);
        purchases.forEach(purchase -> purchase.getPurchaseLists().forEach(purchaseList -> purchaseList.getAlcohol().setCost(currencyService.convertCurrencies(purchaseList.getAlcohol().getCost(), serverCurrency, clientCurrency))));
        return purchases;
    }
}
