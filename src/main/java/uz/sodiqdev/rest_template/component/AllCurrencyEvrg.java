package uz.sodiqdev.rest_template.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AllCurrencyEvrg implements Strategy {
    private List<Strategy> strategies;


    public AllCurrencyEvrg(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Map<String, String> getCurrency(String code) {
        Map<String, String> curren = new HashMap<>();
        float avrg = 0.0F;
        int size = 0;
        for (Strategy strategy : strategies) {
            if (!strategy.getStrategyName().equals(PathType.COMPASITE.name())) {
                Map<String, String> currency = strategy.getCurrency(code);
                String value = currency.get(strategy.getStrategyName().name());
                avrg += Float.parseFloat(value);
                size++;
            }
        }
        curren.put(code, String.valueOf(avrg/size));
        return curren;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.COMPASITE;
    }
}
