package ru.diasoft.integration.currencyexchange.service;


import ru.diasoft.integration.currencyexchange.domains.CurrencyCash;
import ru.diasoft.integration.currencyexchange.domains.RubCash;

import java.math.BigDecimal;

public interface CommissionService {
    RubCash getWithCommission(CurrencyCash currencyCash) throws InterruptedException;
}
