package org.example.exceptions.illegal_operation;

public class PatternNotFoundException extends IllegalOperationException {
    public static final String PATTERN_NOT_FOUND_ERROR = "Pattern for string %s was not found!";

    public PatternNotFoundException(String... args) {
        super(PATTERN_NOT_FOUND_ERROR, args);
    }
}
