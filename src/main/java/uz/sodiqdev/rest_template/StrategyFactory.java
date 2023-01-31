package uz.sodiqdev.rest_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.enam.Url;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StrategyFactory {
    private Map<Url, Strategy> strategies;

    @Autowired
    public StrategyFactory(Set<Strategy> strategies) {
        createStrategy(strategies);
    }

    public Strategy getStrategy(Url url){
       return strategies.get(url);
    }

    private void createStrategy(Set<Strategy> strategy) {
        strategies = new HashMap<Url, Strategy>();
        strategy.forEach(strategy1 -> strategies.put(strategy1.getStrategyName(), strategy1));
    }
}
