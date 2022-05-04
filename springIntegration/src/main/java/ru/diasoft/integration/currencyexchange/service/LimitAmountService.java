package ru.diasoft.integration.currencyexchange.service;

import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;

import java.math.BigDecimal;

public interface LimitAmountService {
    CurrencyCash isAmountCashExceeds (CurrencyCash currencyCash);
}
