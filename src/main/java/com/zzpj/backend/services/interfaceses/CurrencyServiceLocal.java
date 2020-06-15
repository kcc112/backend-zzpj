package com.zzpj.backend.services.interfaceses;

public interface CurrencyServiceLocal {
    Double convertCurrencies(Double value, String from, String to);
}
