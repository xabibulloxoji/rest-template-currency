package uz.sodiqdev.rest_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        addCurrencyToDb();
        Currency currency = currencyRepository.findByCode(code);

        return currency != null ? currency : null;

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


    public Currency getCurrency() {
        return new Currency();
    }

    public List<Currency> getTwoCurrency(String cur1, String cur2) {
        String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";
        ResponseEntity<Currency[]> response =
                restTemplate.getForEntity(url, Currency[].class);

        List<Currency> currencyList = new ArrayList<>();

        Currency current1 = null;
        Currency current2 = null;

        Currency[] currencies = response.getBody();
        for (Currency currency : currencies) {
            if (currency.getCcy().equals(cur1))
                current1 = currency;
            if (currency.getCcy().equals(cur2))
                current2 = currency;
        }
        currencyList.add(current1);
        currencyList.add(current2);
        return currencyList;
    }


}
