package ru.diasoft.integration.currencyexchange.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig
{
    @Value("${rate.USD}")
    private String rateUSD;

    @Value("${rate.EUR}")
    private String rateEUR;

    @Value("${cash.limitAmount}")
    private String limitAmount;

    @Value("${cash.BlackList}")
    private String blackListStr;

    @Value("${cash.commission}")
    private String commission;

    public String getRateUSD()
    {
        return rateUSD;
    }

    public String getLimitAmount()
    {
        return limitAmount;
    }

    public String getBlackListStr()
    {
        return blackListStr;
    }

    public String getCommission() {
        return commission;
    }

    public  String getRateEUR() { return  rateEUR; }
}