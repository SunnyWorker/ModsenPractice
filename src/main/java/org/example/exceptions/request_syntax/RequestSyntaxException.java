package org.example.exceptions.request_syntax;

public class RequestSyntaxException extends RuntimeException {
    public RequestSyntaxException(String message) {
        super(message);
    }

    public RequestSyntaxException(String message, String... args) {
        super(String.format(message, args));
    }
}
