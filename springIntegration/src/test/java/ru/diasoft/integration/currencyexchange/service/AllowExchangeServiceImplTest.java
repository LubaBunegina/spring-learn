package ru.diasoft.integration.currencyexchange.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Проверки на BlackList")
public class AllowExchangeServiceImplTest {

    @Autowired
    private AllowExchangeService service;


    @Test
    @DisplayName("Проверяем, что валюта попала в BlackList")
    void checkCurrencyInBlackList () {
        CurrencyCash curr = new CurrencyCash("ILS",
                BigDecimal.valueOf(100), null, false, false );

        CurrencyCash currAfterCheck = service.currencyInBlackList(curr);
        assertThat(currAfterCheck).hasFieldOrPropertyWithValue("inBlackList", true);
    }

    @Test
    @DisplayName("Проверяем, что валюта НЕ попала в BlackList")
    void checkCurrencyNotInBlackList () {
        CurrencyCash curr = new CurrencyCash("USD",
                BigDecimal.valueOf(100), null, false, false );

        CurrencyCash currAfterCheck = service.currencyInBlackList(curr);
        assertThat(currAfterCheck).hasFieldOrPropertyWithValue("inBlackList", false);
    }

}
