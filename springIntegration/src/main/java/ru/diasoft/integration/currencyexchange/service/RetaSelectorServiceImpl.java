package ru.diasoft.integration.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.integration.currencyexchange.config.AppConfig;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

import java.math.BigDecimal;

@Service("rateService")
public class RetaSelectorServiceImpl implements RateSelectorService {

    @Autowired
    AppConfig appConfig;

    @Override
    public CurrencyCash exchangeRate(CurrencyCash currencyCash) {
        if(currencyCash.getCode().equals("USD")) {
            currencyCash.setRate(new BigDecimal(appConfig.getRateUSD()));
        } else if(currencyCash.getCode().equals("EUR")) {
            currencyCash.setRate(new BigDecimal(appConfig.getRateEUR()));
        }
        return currencyCash;
    }
}
