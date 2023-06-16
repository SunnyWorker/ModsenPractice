package org.example.exceptions.illegal_operation;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message, String... args) {
        super(String.format(message, args));
    }

    public IllegalOperationException(String message) {
        super(message);
    }
}
