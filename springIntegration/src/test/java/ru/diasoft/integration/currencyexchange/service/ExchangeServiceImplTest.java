package ru.diasoft.integration.currencyexchange.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Проверки на расчет суммы в рублях без коммиссии")
public class ExchangeServiceImplTest {

    @Autowired
    private ExchangeService service;

    @Test
    @DisplayName("Проверяем, что сумма в рублях расчитывается")
    void checkThatRubCashCalculate () throws InterruptedException {
        CurrencyCash curr = new CurrencyCash("USD",
                BigDecimal.valueOf(200), null, false, false );
        RubCash rubCash = service.exchange(curr);

        assertThat(rubCash).hasFieldOrProperty("cash");
        assertThat(rubCash.getCash()).isNotZero();
    }
}
