package uz.sodiqdev.rest_template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import uz.sodiqdev.rest_template.serviceImp.Strategy;

@Data
@Component
public class ConnectionOther implements Strategy {

    @Value("${other.url}")
    String url;

    @Value("${token}")
    String token;

    @Override
    public String getCurrency(String code) {
        return null;
    }

//    @Override
//    public String getCurrency(String code, String url, String token) {
//        return "";
//    }
}
