package uz.sodiqdev.rest_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrencyRepository currencyRepository;
    public Currency getCurrencyByCode(String code) {
        Optional<Currency> optionalCurrency = currencyRepository.findByCode(code);
        return optionalCurrency.orElse(null);

    }

    public List<Currency> addCurrencyToDb() {
        String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

        ResponseEntity<Currency[]> response =
                restTemplate.getForEntity(url, Currency[].class);

        Currency[] currencies = response.getBody();

        ArrayList<Currency> currencies1 = new ArrayList<>();

        currencyRepository.deleteAll();

        for (Currency currency : currencies) {
            currencies1.add(currency);
            currencyRepository.save(currency);
        }

        return currencies1;
    }
}
