package com.zzpj.backend.services.implementations;

import com.zzpj.backend.services.interfaceses.CurrencyServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService implements CurrencyServiceLocal {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Double convertCurrencies(Double value, String from, String to) {
        Map<String, Double> result = restTemplate
                .getForObject("https://free.currconv.com/api/v7/convert?apiKey=41aa626d07e4c675953e&compact=ultra&q={from}_{to}",
                        Map.class,
                        from, to);

        return result.get(from + "_" + to) * value;
    }
}
