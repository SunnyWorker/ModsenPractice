package org.example.converter;

public enum OperationType {
    toDollars(2), toRubles(2), add(1), subtract(1);

    OperationType(int operationPriority) {
        this.operationPriority = operationPriority;
    }
    private final int operationPriority;

    public int getOperationPriority() {
        return operationPriority;
    }
}
