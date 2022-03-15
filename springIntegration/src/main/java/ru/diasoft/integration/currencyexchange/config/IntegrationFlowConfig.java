package ru.diasoft.integration.currencyexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

import java.math.BigDecimal;

@Configuration
public class IntegrationFlowConfig {

    @Bean
    public DirectChannel currencyCashChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public PublishSubscribeChannel checkOnMaxAmountChannel() { return MessageChannels.publishSubscribe().get(); }

    @Bean
    public PublishSubscribeChannel checkOnAllowExchangeCurrencyChannel() { return MessageChannels.publishSubscribe().get(); }

    @Bean
    public PublishSubscribeChannel withCommissionChannel() { return MessageChannels.publishSubscribe().get(); }

    @Bean
    public PublishSubscribeChannel withoutCommissionChannel() { return MessageChannels.publishSubscribe().get(); }


    //канал успешно сконвертированной валюты
    @Bean
    public PublishSubscribeChannel exchangeChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    //канал для валюты из черного списка
    @Bean
    public PublishSubscribeChannel blackCurrencyChannel() { return MessageChannels.publishSubscribe().get(); }

    @Bean
    public IntegrationFlow checkOnBlackListFlow() {
        return IntegrationFlows.from("currencyCashChannel")
                .handle("allowExchangeService", "currencyInBlackList")
                .<CurrencyCash, Boolean>route(CurrencyCash::isInBlackList, mapping -> mapping
                        .subFlowMapping(true, rc -> rc.channel("blackCurrencyChannel"))
                        .subFlowMapping(false, rc -> rc.channel("checkOnMaxAmountChannel")))
                .get();
    }

    @Bean
    public IntegrationFlow blackListFlow() {
        return IntegrationFlows.from("blackCurrencyChannel")
                .<CurrencyCash, RubCash>transform(CurrencyCash -> new RubCash(BigDecimal.ZERO))
                .channel("exchangeChannel")
                .get();
    }

    @Bean
    public IntegrationFlow checkOnMaxAmountFlow() {
        return IntegrationFlows.from("checkOnMaxAmountChannel")
                .handle("limitAmountService", "isAmountCashExceeds")
                .<CurrencyCash, Boolean>route(CurrencyCash::isExceed, mapping -> mapping
                        .subFlowMapping(true, rc -> rc.channel("withCommissionChannel"))
                        .subFlowMapping(false, rc -> rc.channel("withoutCommissionChannel")))
                .get();
    }

    @Bean
    public IntegrationFlow withCommissionChannelFlow() {
        return IntegrationFlows.from("withCommissionChannel")
                .handle("rateService", "exchangeRate")
                .handle("commissionService", "getWithCommission")
                .channel("exchangeChannel")
                .get();
    }

    @Bean
    public IntegrationFlow exchangeChannelFlow() {
        return IntegrationFlows.from("withoutCommissionChannel")
                .handle("rateService", "exchangeRate")
                .handle("exchangeService", "exchange")
                .channel("exchangeChannel")
                .get();
    }

}
