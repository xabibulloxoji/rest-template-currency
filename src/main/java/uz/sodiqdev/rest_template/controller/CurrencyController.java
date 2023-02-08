package uz.sodiqdev.rest_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.serviceImp.CurrencyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/update")
    public HttpEntity<?> updateDb(){
        boolean updateDb = currencyService.updateDb();
        return ResponseEntity.status(updateDb? 404: 200).body("Successfully updated");
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
    public HttpEntity<?> getCurrency(@PathVariable String code, @PathVariable String type){
        Map<String, String> currency = currencyService.getCurrency(code, type);
        return ResponseEntity.status(currency.isEmpty()?404:200).body(currency);
    }

    @GetMapping("/dif/{code1}/{code2}")
    public HttpEntity<?> getCurrencyDif(@PathVariable String code1,@PathVariable String code2){
        String dif = currencyService.getCurrencyDif(code1, code2);
        return ResponseEntity.status(dif != null? 200: 404).body(dif);
    }


    @GetMapping("/evrg/{code}")
    public HttpEntity<?> getAllCurrencyRateEvrg(@PathVariable String code){
        Map<String, String> allCurrencyAvrg = currencyService.getAllCurrencyAvrg(code);
        return ResponseEntity.status(allCurrencyAvrg!= null? 200: 404).body(allCurrencyAvrg);
    }

}
