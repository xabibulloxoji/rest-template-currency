package uz.sodiqdev.rest_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{code}")
    public HttpEntity<?> getCurrencyByCode(@PathVariable String code){
        Currency currency = currencyService.getCurrencyByCode(code);

        return ResponseEntity.status(currency != null? 200: 404).body(currency);
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrency(){
        List<Currency> currencies = currencyService.addCurrencyToDb();
        return ResponseEntity.status(currencies.isEmpty()? 404: 200).body(currencies);
    }

    @GetMapping("/one")
    public HttpEntity<?> oneCurrency(){
        Currency currency = currencyService.getCurrency();
        return ResponseEntity.status(currency != null? 200: 404).body(currency);
    }

    @GetMapping("/one")
    public HttpEntity<?> getOneCurrency(){
        Currency currency = currencyService.getCurrency();
        return ResponseEntity.status(currency != null? 200:404).body(currency);
    }



}
