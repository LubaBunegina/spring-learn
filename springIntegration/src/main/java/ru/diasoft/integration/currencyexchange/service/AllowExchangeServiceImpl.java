package ru.diasoft.integration.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.integration.currencyexchange.config.AppConfig;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

@Service("allowExchangeService")
public class AllowExchangeServiceImpl implements AllowExchangeService{
    @Autowired
    private AppConfig appConfig;


    @Override
    public CurrencyCash currencyInBlackList(CurrencyCash curr) {
        String blackListStr = appConfig.getBlackListStr();
        if(blackListStr != null && blackListStr.indexOf(curr.getCode()) > -1) {
            curr.setInBlackList(true);
            System.out.println("Валюта: " + curr.toString() + " in Black List");
        } else {
            curr.setInBlackList(false);
            System.out.println("Валюта: " + curr.toString() + " is NOT in Black List");
        }
        return curr;
    }
}
