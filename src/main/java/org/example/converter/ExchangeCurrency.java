package org.example.converter;

import org.example.converter.enums.Currency;

public record ExchangeCurrency(Currency currencyFrom, Currency currencyTo) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeCurrency that = (ExchangeCurrency) o;

        if (currencyFrom != that.currencyFrom) return false;
        return currencyTo == that.currencyTo;
    }

    @Override
    public int hashCode() {
        int result = currencyFrom.hashCode();
        result = 31 * result + currencyTo.hashCode();
        return result;
    }
}
