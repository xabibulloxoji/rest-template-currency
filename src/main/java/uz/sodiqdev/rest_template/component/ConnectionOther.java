package uz.sodiqdev.rest_template.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.LinkedHashMap;

@Data
@Component
public class ConnectionOther implements Strategy {

    @Value("${other.url}")
    String url;

    @Value("${token}")
    String token;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public String getCurrency(String code) {
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
        return rate;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.OTHER;
    }
}
