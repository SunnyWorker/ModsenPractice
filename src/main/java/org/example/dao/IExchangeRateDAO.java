package org.example.dao;

import org.example.converter.enums.ExchangeCurrency;

import java.util.HashMap;

public interface IExchangeRateDAO {
    HashMap<ExchangeCurrency, Double> getExchangeRates();
}
