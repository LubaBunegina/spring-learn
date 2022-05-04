package ru.diasoft.integration.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.integration.currencyexchange.config.AppConfig;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

import java.math.BigDecimal;

@Service("commissionService")
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private AppConfig appConfig;

    private final ExchangeService exchangeService;

    public CommissionServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }


    @Override
    public RubCash getWithCommission(CurrencyCash currencyCash) throws InterruptedException {
        System.out.println("Валюта: " + currencyCash + " have commission");
        RubCash rc = exchangeService.exchange(currencyCash);
        rc.setCash(rc.getCash().subtract(
                new BigDecimal(appConfig.getCommission()).divide(BigDecimal.valueOf(100)).multiply(rc.getCash())
        ));
        System.out.println("Обмен С УЧЕТОМ  коммиссии " + currencyCash + " завершен. Результат " + rc);
        return rc;
    }
}
