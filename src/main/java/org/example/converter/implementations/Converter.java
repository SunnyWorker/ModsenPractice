package org.example.converter.implementations;

import org.example.converter.enums.Currency;
import org.example.converter.enums.OperationType;
import org.example.converter.interfaces.IConverter;
import org.example.converter.interfaces.IOperationExecutor;
import org.example.dao.ExchangeRateFileSystemImporter;
import org.example.exceptions.RequestSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter implements IConverter {

    private final IOperationExecutor operationExecutor;

    public Converter() {
        this.operationExecutor = new OperationExecutor(new ExchangeRateFileSystemImporter());
    }

    private void checkBracketsCorrectness(String request) {
        int brackets = 0;
        for (int i = 0; i < request.length(); i++) {
            if (request.charAt(i) == '(') brackets++;
            if (request.charAt(i) == ')') brackets--;
            if (brackets < 0)
                throw new RequestSyntaxException("Incorrect using of brackets in request!");
        }
        if (brackets != 0)
            throw new RequestSyntaxException("Incorrect using of brackets in request!");
    }

    private String lookForBrackets(String request) {
        checkBracketsCorrectness(request);
        while (request.contains("(")) {
            StringBuilder stringBuilder = new StringBuilder();
            int brackets = 1;
            int firstBracketId = request.indexOf("(");
            for (int i = firstBracketId + 1; i < request.length(); i++) {
                if (request.charAt(i) == '(') brackets++;
                if (request.charAt(i) == ')') brackets--;
                if (brackets == 0) {
                    request = request.substring(0, firstBracketId)
                            + calculateExpression(stringBuilder.toString())
                            + request.substring(i + 1);
                    break;
                } else stringBuilder.append(request.charAt(i));
            }
        }
        return request;
    }

    private int calculateMaxPriorityOfExpression(String expression) {
        int maxPriority = 0;
        Matcher matcher;
        for (OperationType operationType : OperationType.values()) {
            matcher = operationType.getOperationPattern().matcher(expression);
            if (matcher.find()) {
                maxPriority = Math.max(maxPriority, operationType.getOperationPriority());
            }
        }
        return maxPriority;
    }

    private String calculateExpressionWithoutBrackets(StringBuilder expression) {
        expression = new StringBuilder(expression.toString().replace(" ", ""));
        int maxPriority;
        while ((maxPriority = calculateMaxPriorityOfExpression(expression.toString())) != 0) {
            for (OperationType operationType : OperationType.values()) {
                if (operationType.getOperationPriority() == maxPriority) {
                    Pattern pattern = operationType.getOperationPattern();
                    Matcher matcher = pattern.matcher(expression.toString());
                    while (matcher.find()) {
                        String result = operationExecutor.executeOperation(operationType, matcher.group());
                        expression = new StringBuilder(expression.replace(matcher.start(), matcher.end(), result));
                    }
                }
            }
        }
        if (!Pattern.compile(Currency.getPatternOfAllCurrencies()).matcher(expression).matches())
            throw new RequestSyntaxException("String " + expression + " doesn't match any possible pattern!");
        return expression.toString();
    }

    private String calculateExpression(String request) {
        String unbracketedRequest = lookForBrackets(request);
        return calculateExpressionWithoutBrackets(new StringBuilder(unbracketedRequest));
    }

    @Override
    public String performOperation(String request) {
        return Currency.setFinalPrecision(calculateExpression(request));
    }
}
