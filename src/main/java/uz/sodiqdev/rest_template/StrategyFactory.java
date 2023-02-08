package uz.sodiqdev.rest_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StrategyFactory {
    private Map<PathType, Strategy> strategies;

    public StrategyFactory(Set<Strategy> strategies) {
        createStrategy(strategies);
    }

    public Strategy getStrategy(PathType url){
       return strategies.get(url);
    }

    private void createStrategy(Set<Strategy> strategy) {
        strategies = new HashMap<>();
        strategy.forEach(strategy1 -> strategies.put(strategy1.getStrategyName(), strategy1));
    }
}
