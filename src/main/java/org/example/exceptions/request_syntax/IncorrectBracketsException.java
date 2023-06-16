package org.example.exceptions.request_syntax;

public class IncorrectBracketsException extends RequestSyntaxException {
    public static final String INCORRECT_BRACKETS_ERROR = "Incorrect using of brackets in request!";

    public IncorrectBracketsException() {
        super(INCORRECT_BRACKETS_ERROR);
    }
}
