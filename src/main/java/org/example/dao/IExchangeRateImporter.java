package org.example.dao;

import org.example.converter.enums.ExchangeCurrency;

import java.util.HashMap;

public interface IExchangeRateImporter {
    void calculateExchangeRates();

    HashMap<ExchangeCurrency, Double> getExchangeRates();
}
