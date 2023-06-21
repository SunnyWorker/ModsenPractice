package org.example.exceptions.illegal_operation;

public class ArithmeticDifferentCurrenciesException extends IllegalOperationException {
    public static final String DIFFERENT_CURRENCIES_ARITHMETIC_ERROR = "Operation %s can't be performed with different currencies!";

    public ArithmeticDifferentCurrenciesException(String... args) {
        super(DIFFERENT_CURRENCIES_ARITHMETIC_ERROR, args);
    }
}
