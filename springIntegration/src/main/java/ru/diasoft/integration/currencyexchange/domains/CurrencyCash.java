package ru.diasoft.integration.currencyexchange.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyCash {
    private String code;
    private BigDecimal cash;
    private BigDecimal rate;
    private boolean inBlackList;
    private boolean isExceed;

    @Override
    public String toString() {
        return this.getCash().toString() + " " + this.getCode();
    }
}
