package ru.diasoft.integration.currencyexchange;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;
import ru.diasoft.integration.currencyexchange.gateway.CashGateway;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SpringBootApplication
@IntegrationComponentScan
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class Application {

    private static final String[] CASH_VALUE = { "15", "50.2", "100", "1000", "2000", "85.12", "96.3" };

    private static final String[] CASH_CODE = { "ILS", "INR",  "USD", "EUR" };

    public static void main( String[] args ) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        CashGateway gateway = ctx.getBean(CashGateway.class);


        for(String code : CASH_CODE) {
            for(String value : CASH_VALUE) {
                CurrencyCash currencyCash = new CurrencyCash(code, new BigDecimal(value), null,
                        false, false );
                System.out.println("Получена валюта: " + currencyCash.toString());
                RubCash rubCash = gateway.exchange(currencyCash);
                System.out.println("Результат обмена по курсу: " + rubCash.toString());
            }
        }

    }





}
