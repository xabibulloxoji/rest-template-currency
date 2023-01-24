package uz.sodiqdev.rest_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("{code}")
    public Currency getCurrencyByCode(@PathVariable String code){
        Currency currency = currencyService.getCurrencyByCode(code);
        return currency;
    }

    @PostMapping
    public List<Currency> addCurrencyToDb(){
        List<Currency> currencies = currencyService.addCurrencyToDb();
        return currencies;
    }

}
