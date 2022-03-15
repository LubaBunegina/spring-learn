package ru.diasoft.integration.currencyexchange.service;

import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

public interface  AllowExchangeService {
    CurrencyCash currencyInBlackList (CurrencyCash curr);
}
