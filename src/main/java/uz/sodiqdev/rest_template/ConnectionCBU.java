package uz.sodiqdev.rest_template;

import lombok.Data;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.serviceImp.Strategy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;

@Data
@Component
public class ConnectionCBU implements Strategy {

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
//                String inline = "\"CurrencyArray\":";
                String inline = "";
                Scanner scanner = new Scanner(url1.openStream());

                while (scanner.hasNext()) inline += scanner.nextLine();
                scanner.close();
                System.out.println(inline);

                if(inline.charAt(0) == '['){
                    inline = inline.substring(1, inline.length() - 1);
                }
                JSONObject jsonObject = new JSONObject(inline);


                Set<String> keys =jsonObject.keySet();
                for(String key:keys) {
                    System.out.println("Key :: "+key +", Value :: "+jsonObject.get(key));;
                }
//                JSONParser parser = new JSONParser(inline);
//                var linkedHashMap = parser.object();
//                Set<String> keySet = linkedHashMap.keySet();
//                for(String key: keySet){
//                    var value = linkedHashMap.get(key);
//                    JSONParser jsonParser = new JSONParser((String) linkedHashMap.get(key));
//                    var innerLinkedMap = jsonParser.object();
//                    Set<String> keys = innerLinkedMap.keySet();
//                    System.out.println(key + ":\n\t" + value + ": ");
//                    for(String k: keys){
//                        var v = linkedHashMap.get(key);
//                        System.out.println(k + ": " + v + "\n");
//                    }
//                }
//                JSONObject object = new JSONObject(parser);
//
//                JSONArray array = new JSONArray(object);
//
//                System.out.println(array);
                JSONArray array = new JSONArray();

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

}
