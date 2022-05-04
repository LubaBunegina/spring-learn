package ru.diasoft.integration.currencyexchange.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RubCash {
    private BigDecimal cash;

    @Override
    public String toString() {
        return this.getCash().toString();
    }
}
