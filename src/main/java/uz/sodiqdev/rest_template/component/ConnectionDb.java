package uz.sodiqdev.rest_template.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class ConnectionDb implements Strategy {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Map<String, String> getCurrency(String code){
        Map<String, String> currencies = new HashMap<>();
        Optional<Currency> optionalCurrency = currencyRepository.findByCcy(code);
        if (optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            currencies.put(getStrategyName().name(), currency.getRate());
            return currencies;
        }
        return null;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.LOCAL;
    }
}
