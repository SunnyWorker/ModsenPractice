package org.example.converter.interfaces;

import org.example.converter.enums.OperationType;

public interface IOperationExecutor {
    String executeOperation(OperationType operationType, String operationString);
}
