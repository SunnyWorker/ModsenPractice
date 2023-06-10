package org.example.converter.enums;

import java.util.regex.Pattern;

public enum OperationType {
    toDollars(2, "toDollars", Pattern.compile("toDollars"+Currency.getPatternOfAllCurrenciesEx(Currency.dollarUSA))),
    toRubles(2, "toRubles", Pattern.compile("toRubles"+Currency.getPatternOfAllCurrenciesEx(Currency.russianRuble))),
    add(1,"+", Pattern.compile(Currency.getPatternOfAllCurrencies()+"\\+"+Currency.getPatternOfAllCurrencies())),
    subtract(1, "-", Pattern.compile(Currency.getPatternOfAllCurrencies()+"-"+Currency.getPatternOfAllCurrencies()));

    OperationType(int operationPriority, String operationWord, Pattern operationPattern) {
        this.operationPriority = operationPriority;
        this.operationWord = operationWord;
        this.operationPattern = operationPattern;
    }

    private final int operationPriority;
    private final String operationWord;
    private final Pattern operationPattern;

    public int getOperationPriority() {
        return operationPriority;
    }

    public String getOperationWord() {
        return operationWord;
    }

    public Pattern getOperationPattern() {
        return operationPattern;
    }
}
