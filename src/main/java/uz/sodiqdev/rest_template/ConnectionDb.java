package uz.sodiqdev.rest_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;
import uz.sodiqdev.rest_template.service.CurrencyService;
import uz.sodiqdev.rest_template.serviceImp.Strategy;


@Component
public class ConnectionDb implements Strategy {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public String getCurrency(String code) {
        Currency currency = currencyRepository.findByCcy(code);
        return currency.getRate();
    }
}
