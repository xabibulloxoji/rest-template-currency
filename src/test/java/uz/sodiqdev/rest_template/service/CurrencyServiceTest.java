package uz.sodiqdev.rest_template.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.sodiqdev.rest_template.entity.Currency;
import uz.sodiqdev.rest_template.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.linesOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;

    @Test
    void getCurrencyByCodeTest() {
        Currency currency = currencyService.getCurrencyByCode("840");
        assertEquals("USD", currency.getCcy());
        addCurrencyToDbTest();
    }


    void addCurrencyToDbTest() {
        List<Currency> all = currencyRepository.findAll();
        assertNotNull(all);
        int size = all.size();
        assertEquals(75, size);
        all.forEach(currency -> {
            assertNotNull(currency.getCcy());
            assertNotNull(currency.getCode());
        });
    }




}