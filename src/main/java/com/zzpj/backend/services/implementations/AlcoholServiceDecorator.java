package com.zzpj.backend.services.implementations;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.services.interfaceses.AlcoholServiceLocal;
import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Qualifier("alcoholServiceWithCurrencies")
public class AlcoholServiceDecorator implements AlcoholServiceLocal {

    final private AlcoholServiceLocal alcoholService;
    final private CurrencyServiceLocal currencyService;
    private String serverCurrency = "USD";
    private String clientCurrency = "PLN";

    @Autowired
    public AlcoholServiceDecorator(AlcoholServiceLocal alcoholService, CurrencyServiceLocal currencyService) {
        this.alcoholService = alcoholService;
        this.currencyService = currencyService;
    }

    @Override
    public void addAlcohol(Alcohol alcohol) throws AppBaseException {
        alcohol.setCost(currencyService.convertCurrencies(alcohol.getCost(), clientCurrency, serverCurrency));
        alcoholService.addAlcohol(alcohol);
    }

    @Override
    public Optional<Alcohol> getAlcohol(UUID id) {
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(id);
        alcohol.ifPresent(a -> a.setCost(currencyService.convertCurrencies(a.getCost(), serverCurrency, clientCurrency)));
        return alcohol;
    }

    @Override
    public List<Alcohol> getAllAlcohols() {
        List<Alcohol> alcohols = alcoholService.getAllAlcohols();
        alcohols.forEach(alcohol -> alcohol.setCost(currencyService.convertCurrencies(alcohol.getCost(), serverCurrency, clientCurrency)));
        return alcohols;
    }

    @Override
    public void deleteAlcohol(UUID id) throws AppBaseException {
        alcoholService.deleteAlcohol(id);
    }

    @Override
    public void editAlcohol(Alcohol alcohol) throws AppBaseException {
        alcohol.setCost(currencyService.convertCurrencies(alcohol.getCost(), clientCurrency, serverCurrency));
        alcoholService.editAlcohol(alcohol);
    }
}
