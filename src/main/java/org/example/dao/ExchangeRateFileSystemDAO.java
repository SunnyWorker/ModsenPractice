package org.example.dao;

import org.example.converter.enums.Currency;
import org.example.converter.enums.ExchangeCurrency;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ExchangeRateFileSystemDAO implements IExchangeRateDAO {
    private static final String configurationFilename = "exchange_rates.bank";
    private Scanner configurationFileScanner;
    private HashMap<ExchangeCurrency, Double> exchangeRates;

    public ExchangeRateFileSystemDAO() {
        openFileScanner();
        calculateExchangeRates();
    }

    private void openFileScanner() {
        try {
            this.configurationFileScanner = new Scanner(new FileInputStream("src/main/resources/"+configurationFilename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void calculateExchangeRates() {
        exchangeRates = new HashMap<>();
        while(configurationFileScanner.hasNextLine()) {
            Currency currencyFrom = Currency.valueOf(configurationFileScanner.next());
            Currency currencyTo = Currency.valueOf(configurationFileScanner.next());
            Double rate = Double.valueOf(configurationFileScanner.next());
            exchangeRates.put(new ExchangeCurrency(currencyFrom, currencyTo), rate);
        }
    }

    @Override
    public HashMap<ExchangeCurrency, Double> getExchangeRates() {
        return exchangeRates;
    }
}
