package org.example.exceptions;

public class RequestSyntaxException extends RuntimeException {
    public RequestSyntaxException(String message) {
        super(message);
    }
}
