package ru.diasoft.integration.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.integration.currencyexchange.config.AppConfig;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

import java.math.BigDecimal;


@Service("limitAmountService")
public class LimitAmountServiceImpl implements  LimitAmountService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public CurrencyCash isAmountCashExceeds(CurrencyCash currencyCash) {
        if(currencyCash.getCash().compareTo(new BigDecimal(appConfig.getLimitAmount())) > 0) {
            currencyCash.setExceed(true);
        } else {
            currencyCash.setExceed(false);
        }
        return currencyCash;
    }
}
