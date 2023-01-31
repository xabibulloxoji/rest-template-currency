package uz.sodiqdev.rest_template.component;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.entity.enam.Url;
import uz.sodiqdev.rest_template.service.Strategy;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component(value = "Conne")
public class ConnectionCBU implements Strategy {

    public static final String CONNECTION_CBU_BEAN = "Qualifier";
    @Value("${cbu.url}")
    String url;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getCurrency(String code) {
        String rate = "";
        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");

            int responceCode = conn.getResponseCode();

            if (responceCode != 200) {
                throw new RuntimeException("HttpResponceCode: " + responceCode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url1.openStream());

                while (scanner.hasNext()) inline += scanner.nextLine();
                scanner.close();
                System.out.println(inline);

                JSONArray array = new JSONArray(inline);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject new_obj = (JSONObject) array.get(i);
                    if (new_obj.get("Ccy").equals(code)) {
                        System.out.println(new_obj.get("Rate"));
                        rate = new_obj.get("Rate").toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }

    @Override
    public Url getStrategyName() {
        return Url.CBU;
    }

}
