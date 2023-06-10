package org.example.converter.enums;

public enum Currency {
    dollarUSA("\\$-?\\d+(\\.\\d+)?"), russianRuble("-?\\d+(\\.\\d+)?р");
    private String pattern;

    Currency(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
    public static String getPatternOfAllCurrenciesEx(Currency exceptedCurrency) {
        StringBuilder pattern = new StringBuilder("(");
        for (Currency value : Currency.values()) {
            if (value.equals(exceptedCurrency)) continue;
            pattern.append(value.getPattern()).append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1).append(")");
        return pattern.toString();
    }

    public static String getPatternOfAllCurrencies() {
        StringBuilder pattern = new StringBuilder("(");
        for (Currency value : Currency.values()) {
            pattern.append(value.getPattern()).append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1).append(")");
        return pattern.toString();
    }

    public String convertNumberIntoCurrency(String number) {
        switch (this) {
            case dollarUSA -> {
                return "$"+number;
            }
            case russianRuble -> {
                return number+"р";
            }
            default -> {
                return number;
            }
        }
    }


}
