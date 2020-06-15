package com.zzpj.backend.services.interfaceses;

import java.util.List;

public interface CurrencyServiceLocal {
    Double convertCurrencies(Double value, String from, String to);
}
