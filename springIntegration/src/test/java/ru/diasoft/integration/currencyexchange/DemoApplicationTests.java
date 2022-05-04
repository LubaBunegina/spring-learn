package ru.diasoft.integration.currencyexchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;
import ru.diasoft.integration.currencyexchange.gateway.CashGateway;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private  CashGateway gateway;

	@Test
	void chechExchangeCurrencyInNotInBlackListWithCommission() {

		RubCash rc = gateway.exchange(new CurrencyCash(
				"USD", BigDecimal.valueOf(2000), null, false, false));

		assertThat(rc).hasFieldOrProperty("cash");
		assertThat(rc.getCash()).isGreaterThan(BigDecimal.ZERO);
	}

	@Test
	void chechExchangeCurrencyInBlackList() {

		RubCash rc = gateway.exchange(new CurrencyCash(
				"KRW", BigDecimal.valueOf(2000), null, false, false));

		assertThat(rc).hasFieldOrProperty("cash");
		assertThat(rc.getCash()).isZero();
	}

}
