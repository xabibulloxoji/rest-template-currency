package uz.sodiqdev.rest_template.serviceImp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.sodiqdev.rest_template.StrategyFactory;
import uz.sodiqdev.rest_template.component.AllCurrencyEvrg;
import uz.sodiqdev.rest_template.component.ConnectionCBU;
import uz.sodiqdev.rest_template.component.ConnectionDb;
import uz.sodiqdev.rest_template.component.ConnectionOther;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.entity.enam.PathType;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;
import uz.sodiqdev.rest_template.service.Strategy;

import java.util.*;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;
    private final ConnectionCBU connectionCBU;
    private final ConnectionDb connectionDb;
    private final ConnectionOther connectionOther;
    private final StrategyFactory strategyFactory;

    private final AllCurrencyEvrg allCurrencyEvrg;

    public CurrencyService(
            RestTemplate restTemplate,
            CurrencyRepository currencyRepository,
            ConnectionCBU connectionCBU,
            ConnectionDb connectionDb,
            ConnectionOther connectionOther,
            StrategyFactory strategyFactory, AllCurrencyEvrg allCurrencyEvrg) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
        this.connectionCBU = connectionCBU;
        this.connectionDb = connectionDb;
        this.connectionOther = connectionOther;
        this.strategyFactory = strategyFactory;
        this.allCurrencyEvrg = allCurrencyEvrg;
    }



    @Scheduled(fixedRate = 500000)
    public boolean updateDb() {
        String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

        ResponseEntity<Currency[]> response =
                restTemplate.getForEntity(url, Currency[].class);

        Currency[] currencies = response.getBody();

        ArrayList<Currency> currencies1 = new ArrayList<>();

        currencyRepository.deleteAll();

        for (Currency currency : currencies) {
            currencies1.add(currency);
            currencyRepository.save(currency);
        }
        System.out.println("Successfully updated");
        List<Currency> currencyList = currencyRepository.findAll();
        boolean empty = currencyList.isEmpty();
        return empty;
    }

    public Currency getCurrencyByCode(String code) {
        Optional<Currency> optionalCurrency = currencyRepository.findByCcy(code);
        Currency currency = optionalCurrency.get();
        return currency;
    }

    public List<Currency> getAllCurrency(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Currency> currencies = currencyRepository.findAll(pageable);
        return currencies.getContent();
    }

    public Map<String, String> getCurrency(String code, String type) {
        Strategy strategy = strategyFactory.getStrategy(PathType.valueOf(type));
        return strategy.getCurrency(code);
    }

    public String getCurrencyDif(String cur1, String cur2) {

        String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";
        ResponseEntity<Currency[]> response =
                restTemplate.getForEntity(url, Currency[].class);

        List<Currency> currencyList = new ArrayList<>();

        Currency current1 = null;
        Currency current2 = null;

        Currency[] currencies = response.getBody();
        for (Currency currency : currencies) {
            if (currency.getCcy().equals(cur1))
                current1 = currency;
            if (currency.getCcy().equals(cur2))
                current2 = currency;
        }
        currencyList.add(current1);
        currencyList.add(current2);
        for (Currency currency : currencyList) {
            if (currency == null) {
                return null;
            }
        }
        float currency1 = Float.parseFloat(currencyList.get(0).getRate());
        float currency2 = Float.parseFloat(currencyList.get(1).getRate());
        return String.valueOf(currency1 - currency2);
    }

    public Map<String, String> getAllCurrencyAvrg(String code){
        return allCurrencyEvrg.getCurrency(code);
    }

}
