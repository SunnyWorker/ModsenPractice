package org.example.exceptions.illegal_operation;

public class RecursiveConvertException extends IllegalOperationException {
    public static final String RECURSIVE_CONVERT_ERROR = "You can't transform currencies into themselves!";

    public RecursiveConvertException() {
        super(RECURSIVE_CONVERT_ERROR);
    }
}
