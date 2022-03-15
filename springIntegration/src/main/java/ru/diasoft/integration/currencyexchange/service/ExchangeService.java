package ru.diasoft.integration.currencyexchange.service;

import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

public interface ExchangeService {
    RubCash exchange(CurrencyCash curCash) throws InterruptedException;
}
