package ru.diasoft.integration.currencyexchange.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Проверки на необходимость взятия коммиссии")
public class LimitAmountServiceImplTest {

    @Autowired
    private LimitAmountService service;

    @Test
    @DisplayName("Проверяем, что установился флаг isExceed=true")
    void checkThatCurrencyHaveIsExceedFlagTrue () {
        CurrencyCash curr = new CurrencyCash("USD",
                BigDecimal.valueOf(20000), null, false, false );

        CurrencyCash currAfterCheck = service.isAmountCashExceeds(curr);

        assertThat(currAfterCheck).hasFieldOrPropertyWithValue("isExceed", true);
    }

    @Test
    @DisplayName("Проверяем, что установился флаг isExceed=false")
    void checkThatCurrencyHaveIsExceedFlagFalse () {
        CurrencyCash curr = new CurrencyCash("USD",
                BigDecimal.valueOf(200), null, false, false );

        CurrencyCash currAfterCheck = service.isAmountCashExceeds(curr);

        assertThat(currAfterCheck).hasFieldOrPropertyWithValue("isExceed", false);
    }

}
