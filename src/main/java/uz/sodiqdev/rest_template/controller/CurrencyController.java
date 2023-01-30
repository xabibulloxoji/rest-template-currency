package uz.sodiqdev.rest_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
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

    @GetMapping("/one/{ccy}")
    public HttpEntity<?> oneCurrency(@RequestParam String url, @PathVariable String ccy){
        Currency currency = currencyService.getCurrency1(url, ccy);
        return ResponseEntity.status(currency != null? 200: 404).body(currency);
    }

    @GetMapping("/two")
    public HttpEntity<?> getTwoCurrency(@RequestParam String ccy1,@RequestParam String ccy2){
        List<Currency> twoCurrency = currencyService.getTwoCurrency(ccy1, ccy2);
        return ResponseEntity.status(twoCurrency != null? 200: 404).body(twoCurrency);
    }


    @GetMapping("/get/{code}")
    public void getCurrency(@PathVariable String code){
        currencyService.getCurrency(code);
    }

}
