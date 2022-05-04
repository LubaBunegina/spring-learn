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
@DisplayName("Проверки на расчет коммиссии")
public class CommissionServiceImplTest {

    @Autowired
    private CommissionService service;

    @Test
    @DisplayName("Проверяем, что коммиссия расчитывается")
    void checkThatCommissionCalculate () throws InterruptedException {
        CurrencyCash curr = new CurrencyCash("USD",
                BigDecimal.valueOf(2000), null, false, false );
        RubCash rubCash = service.getWithCommission(curr);

        assertThat(rubCash).hasFieldOrProperty("cash");
        assertThat(rubCash.getCash()).isNotZero();
    }
}
