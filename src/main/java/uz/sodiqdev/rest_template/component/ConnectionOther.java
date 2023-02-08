package uz.sodiqdev.rest_template.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ConnectionOther implements Strategy {

    @Value("${other.url}")
    String url;

    @Value("${token}")
    String token;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Map<String, String> getCurrency(String code) {
        Map<String, String> currencies = new HashMap<>();
        String rate = "";
        Object object =
                restTemplate.getForObject(url + "?apikey=" + token + "&base_currency=" + code +
                        "&currency=UZS", Object.class);
        LinkedHashMap<Object, Object> hashMap = (LinkedHashMap<Object, Object>) object;
        LinkedHashMap<Object, Object> value = (LinkedHashMap<Object, Object>) hashMap.get("data");
        LinkedHashMap<Object, Object> root = (LinkedHashMap<Object, Object>) value.get("UZS");
        Object value1 = root.get("value");
        float answer = Float.parseFloat(String.valueOf(value1));
        rate = String.format("%.02f", answer);
        currencies.put(getStrategyName().name(), rate);
        return currencies;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.OTHER;
    }
}
