package uz.sodiqdev.rest_template.component;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.entity.enam.Url;
import uz.sodiqdev.rest_template.service.Strategy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Data
@Component
public class ConnectionOther implements Strategy {

    @Value("${other.url}")
    String url;

    @Value("${token}")
    String token;


    @Override
    public String getCurrency(String code) {
        String rate = "";
        try {
            URL url1 = new URL(url + "?base_currency="+code+"&currency=UZS");
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestProperty("apikey", token);
            conn.setRequestMethod("GET");

            int responceCode = conn.getResponseCode();

            if (responceCode != 200) {
                throw new RuntimeException("HttpResponceCode: " + responceCode);
            } else {
                String inline;

                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((inline = bufferedReader.readLine()) != null){
                    stringBuilder.append(inline);
                }
                bufferedReader.close();

                JSONObject json = new JSONObject(stringBuilder.toString());
                float value = json.getJSONObject("data").getJSONObject("UZS").getFloat("value");
                JSONObject data = json.getJSONObject("data").getJSONObject(code);
                System.out.println(data);
                rate = String.valueOf(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }

    @Override
    public Url getStrategyName() {
        return Url.OTHER;
    }
}
