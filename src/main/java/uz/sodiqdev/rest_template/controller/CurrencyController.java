package uz.sodiqdev.rest_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.serviceImp.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/update")
    public ResponseEntity<List<Currency>> updateDb(){
        List<Currency> currencies = currencyService.updateDb();
        return ResponseEntity.status(currencies.isEmpty()? 404: 200).body(currencies);
    }


    @GetMapping("/get/{code}")
    public HttpEntity<?> getCurrencyByCode(@PathVariable String code){
        Currency currency = currencyService.getCurrencyByCode(code);
        return ResponseEntity.status(currency != null? 200: 404).body(currency);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAllCurrency(@RequestParam int page, @RequestParam int size){
        List<Currency> currencies = currencyService.getAllCurrency(page, size);
        return ResponseEntity.status(currencies != null? 200: 404).body(currencies);
    }

    @GetMapping("/get/{code}/{type}")
    public String getCurrency(@PathVariable String code, @PathVariable String type){
        return currencyService.getCurrency(code, type);
    }

    @GetMapping("/diff/{code1}/{code2}")
    public HttpEntity<?> getTwoCurrencyDif(@PathVariable String code1,@PathVariable String code2){
        currencyService.getTwoCurrencyDif(code1, code2);
        return null;
    }




}
