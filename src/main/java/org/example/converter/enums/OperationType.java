package org.example.converter.enums;

import java.util.regex.Pattern;

public enum OperationType {
    TO_DOLLARS(2, Pattern.compile(OperationType.toDollarsOperation + Currency.getPatternOfAllCurrencies())),
    TO_RUBLES(2, Pattern.compile(OperationType.toRublesOperation + Currency.getPatternOfAllCurrencies())),
    ADD(1, Pattern.compile(Currency.getPatternOfAllCurrencies() + "\\+" + Currency.getPatternOfAllCurrencies())),
    SUBTRACT(1, Pattern.compile(Currency.getPatternOfAllCurrencies() + "-" + Currency.getPatternOfAllCurrencies()));

    public static final String toDollarsOperation = "toDollars";
    public static final String toRublesOperation = "toRubles";

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
