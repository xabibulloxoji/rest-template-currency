package uz.sodiqdev.rest_template.component;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.HashMap;
import java.util.Map;

@Component(value = "Conne")
public class ConnectionCBU implements Strategy {

    public static final String CONNECTION_CBU_BEAN = "Qualifier";
    @Value("${cbu.url}")
    String url;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Map<String, String> getCurrency(String code) {
        Map<String, String> currencies = new HashMap<>();
        String rate = "";
        Object[] object = restTemplate.getForObject(url, Object[].class);

        JSONArray array = new JSONArray(object);
        for (Object currency : array) {
            JSONObject new_obj = (JSONObject) currency;
            if (new_obj.get("Ccy").equals(code)) {
                System.out.println(new_obj.get("Rate"));
                rate = new_obj.get("Rate").toString();
            }
        }
        currencies.put(getStrategyName().name(), rate);
        return currencies;
    }

    @Override
    public PathType getStrategyName() {
        return PathType.CBU;
    }

}
