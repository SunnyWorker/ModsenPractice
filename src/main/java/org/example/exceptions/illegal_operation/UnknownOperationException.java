package org.example.exceptions.illegal_operation;

public class UnknownOperationException extends IllegalOperationException {
    public static final String UNKNOWN_OPERATION_ERROR = "Unknown operation detected!";

    public UnknownOperationException() {
        super(UNKNOWN_OPERATION_ERROR);
    }
}
