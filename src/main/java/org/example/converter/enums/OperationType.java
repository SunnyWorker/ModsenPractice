package org.example.converter.enums;

import java.util.regex.Pattern;

public enum OperationType {
    toDollars(2, Pattern.compile("toDollars"+Currency.getPatternOfAllCurrencies())),
    toRubles(2, Pattern.compile("toRubles"+Currency.getPatternOfAllCurrencies())),
    add(1,Pattern.compile(Currency.getPatternOfAllCurrencies()+"\\+"+Currency.getPatternOfAllCurrencies())),
    subtract(1, Pattern.compile(Currency.getPatternOfAllCurrencies()+"-"+Currency.getPatternOfAllCurrencies()));

    OperationType(int operationPriority, Pattern operationPattern) {
        this.operationPriority = operationPriority;
        this.operationPattern = operationPattern;
    }

    private final int operationPriority;
    private final Pattern operationPattern;

    public int getOperationPriority() {
        return operationPriority;
    }

    public Pattern getOperationPattern() {
        return operationPattern;
    }
}
