package org.example.converter.enums;

import org.example.exceptions.illegal_operation.PatternNotFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Currency {
    DOLLAR_USA("\\$-?\\d+(,\\d+)?"), RUSSIAN_RUBLE("-?\\d+(,\\d+)?р");
    private final String pattern;

    Currency(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public static String getPatternOfAllCurrencies() {
        StringBuilder pattern = new StringBuilder("(");
        for (Currency value : Currency.values()) {
            pattern.append(value.getPattern()).append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1).append(")");
        return pattern.toString();
    }

    public static Currency getCurrencyFromString(String number) {
        for (Currency value : Currency.values()) {
            Pattern pattern = Pattern.compile(value.getPattern());
            Matcher matcher = pattern.matcher(number);
            if (matcher.find())
                return value;
        }
        throw new PatternNotFoundException(number);
    }

    public String convertNumberIntoCurrency(String number) {
        switch (this) {
            case DOLLAR_USA -> {
                return "$" + number;
            }
            case RUSSIAN_RUBLE -> {
                return number + "р";
            }
            default -> throw new PatternNotFoundException(number);
        }
    }

    public static String setFinalPrecision(String number) {
        Currency usedCurrency = Currency.getCurrencyFromString(number);
        Pattern pattern = Pattern.compile("-?\\d+(,\\d+)?");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find())
            number = matcher.group();
        return usedCurrency.convertNumberIntoCurrency(String.format("%.2f", Currency.parseToDouble(number)));
    }

    public static Double parseToDouble(String number) {
        return Double.parseDouble(number.replace(",", "."));
    }

    public static String parseToString(Double number) {
        return String.valueOf(number).replace(".", ",");
    }
}
