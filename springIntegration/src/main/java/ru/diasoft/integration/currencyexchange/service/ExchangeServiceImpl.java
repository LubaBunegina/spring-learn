package ru.diasoft.integration.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.diasoft.integration.currencyexchange.config.AppConfig;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

import java.math.BigDecimal;

@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private  AppConfig appConfig;

    @Override
    public RubCash exchange(CurrencyCash curCash) throws InterruptedException {
        String curr = curCash.toString();
        System.out.println("Обмен " + curr);
        Thread.sleep(3000);
        RubCash rubCash = new RubCash(new BigDecimal(String.valueOf(new BigDecimal(appConfig.getRateUSD()).multiply(curCash.getCash()))));
        System.out.println("Обмен БЕЗ УЧЕТА коммиссии " + curr + " завершен. Результат " + rubCash.toString());
        return rubCash;
    }
}
