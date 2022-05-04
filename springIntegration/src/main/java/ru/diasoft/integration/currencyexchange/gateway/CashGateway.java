package ru.diasoft.integration.currencyexchange.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;


@MessagingGateway
public interface CashGateway{

    @Gateway(requestChannel = "currencyCashChannel", replyChannel = "exchangeChannel")
    RubCash exchange(CurrencyCash cash);
}
