package org.example.converter.enums;

import org.example.exceptions.IllegalOperationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Currency {
    dollarUSA("\\$-?\\d+(,\\d+)?"), russianRuble("-?\\d+(,\\d+)?р");
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

    public static Currency getPatternFromString(String number) {
        for (Currency value : Currency.values()) {
            Pattern pattern = Pattern.compile(value.getPattern());
            Matcher matcher = pattern.matcher(number);
            if (matcher.find())
                return value;
        }
        throw new IllegalOperationException("Pattern for string " + number + " was not found!");
    }

    public String convertNumberIntoCurrency(String number) {
        switch (this) {
            case dollarUSA -> {
                return "$" + number;
            }
            case russianRuble -> {
                return number + "р";
            }
            default -> {
                return number;
            }
        }
    }

    public static String setFinalPrecision(String number) {
        Currency usedCurrency = Currency.getPatternFromString(number);
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
