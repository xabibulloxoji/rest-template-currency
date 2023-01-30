package uz.sodiqdev.rest_template.service;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.ConnectionCBU;
import uz.sodiqdev.rest_template.ConnectionDb;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.entity.enam.Url;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;
import uz.sodiqdev.rest_template.serviceImp.Strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CurrencyService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrencyRepository currencyRepository;


    @Autowired
    ConnectionCBU connectionCBU;

    @Autowired
    ConnectionDb connectionDb;


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


    public Currency getCurrency1(String url, String ccy) {
        if (url != null) {

            Url url1 = Url.valueOf(url);
            String url2 = url1.getUrl();

            ResponseEntity<Currency[]> response = restTemplate.getForEntity(url2,
                    Currency[].class);
            Currency currency;
            Currency[] body = response.getBody();
            for (Currency currency1 : body) {
                if (currency1.getCcy().equals(ccy)) {
                    currency = currency1;
                    return currency;
                }
            }
        } else {
            Currency currency = currencyRepository.findByCcy(ccy);
            return currency;
        }


        return null;
//        return currencyRepository.findByCode("050");
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
        for (Currency currency : currencyList) {
            if (currency == null) {
                return null;
            }
        }
        return currencyList;
    }


    public String getCurrencyCode(String code) {
        Currency currency = currencyRepository.findByCode(code);
        return currency.getRate();
    }


    public String getCurrency(String code) {
        return connectionCBU.getCurrency(code);
    }


}
