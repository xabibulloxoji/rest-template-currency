package uz.sodiqdev.rest_template.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.List;

@Component
public class AllCurrencyEvrg implements Strategy {
    private List<Strategy> strategies;


    @Autowired
    public AllCurrencyEvrg(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String getCurrency(String code) {
        float avrg = 0.0F;
        int size = 0;
        for (Strategy strategy : strategies) {
            if (!strategy.getStrategyName().equals(PathType.COMPASITE.name())) {
                String currency = strategy.getCurrency(code);
                avrg += Float.parseFloat(currency);
                size++;
            }
        }
        return String.valueOf(avrg/size);
    }

    @Override
    public PathType getStrategyName() {
        return PathType.COMPASITE;
    }
}
