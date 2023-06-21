package org.example.exceptions.request_syntax;

public class PatternNotFoundException extends RequestSyntaxException {
    public static final String PATTERN_NOT_FOUND_ERROR = "String %s doesn't match any possible pattern!";

    public PatternNotFoundException(String... args) {
        super(PATTERN_NOT_FOUND_ERROR, args);
    }
}
