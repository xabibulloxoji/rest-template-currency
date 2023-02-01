package uz.sodiqdev.rest_template.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.Optional;


@Component
public class ConnectionDb implements Strategy {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public String getCurrency(String code) {
        Optional<Currency> optionalCurrency = currencyRepository.findByCcy(code);
        if (optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            return currency.getRate();
        }
        return null;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.LOCAL;
    }
}
