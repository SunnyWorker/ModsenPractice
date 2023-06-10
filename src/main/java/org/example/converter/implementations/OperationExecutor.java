package org.example.converter.implementations;

import org.example.converter.enums.Currency;
import org.example.converter.enums.ExchangeCurrency;
import org.example.converter.enums.OperationType;
import org.example.converter.interfaces.IOperationExecutor;
import org.example.dao.IExchangeRateDAO;
import org.example.exceptions.IllegalOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationExecutor implements IOperationExecutor {

    private final IExchangeRateDAO exchangeRateDAO;

    public OperationExecutor(IExchangeRateDAO exchangeRateDAO) {
        this.exchangeRateDAO = exchangeRateDAO;
    }

    private String executeExchangeRatesOperation(String operationString, Currency currency) {
        for (Currency value : Currency.values()) {
            Pattern pattern = Pattern.compile(value.getPattern());
            Matcher matcher = pattern.matcher(operationString);
            if(matcher.find()) {
                if(value.equals(currency)) throw new IllegalOperationException("You can't transform currencies into themselves!");
                String number = matcher.group();
                pattern = Pattern.compile("-?\\d+(,\\d+)?");
                matcher = pattern.matcher(number);
                if(matcher.find())
                    number = matcher.group();
                double doubleNumber = Currency.parseToDouble(number);
                return Currency.parseToString(doubleNumber*exchangeRateDAO.getExchangeRates().get(new ExchangeCurrency(value, currency)));
            }
        }
        throw new IllegalOperationException("Unknown operation!");
    }

    public String executeOperation(OperationType operationType, String operationString) {
        switch (operationType) {
            case add -> {
                List<String> operands = new ArrayList<>(2);
                double operand1 = 0, operand2 = 0;
                Currency usedCurrency = Currency.dollarUSA;
                for (Currency value : Currency.values()) {
                    Pattern pattern = Pattern.compile(value.getPattern());
                    Matcher matcher = pattern.matcher(operationString);
                    while(matcher.find())
                        operands.add(matcher.group());
                    if(operands.size()==1) throw new IllegalOperationException("Addition of different currencies is illegal!");
                    if(operands.size()==2) {
                        usedCurrency = value;
                        break;
                    }
                }
                Pattern pattern = Pattern.compile("-?\\d+(,\\d+)?");
                Matcher matcher = pattern.matcher(operands.get(0));
                if(matcher.find())
                    operand1 = Currency.parseToDouble(matcher.group());
                matcher = pattern.matcher(operands.get(1));
                if(matcher.find())
                    operand2 = Currency.parseToDouble(matcher.group());
                return usedCurrency.convertNumberIntoCurrency(Currency.parseToString(operand1+operand2));
            }
            case subtract -> {
                List<String> operands = new ArrayList<>(2);
                double operand1 = 0, operand2 = 0;
                Currency usedCurrency = Currency.dollarUSA;
                for (Currency value : Currency.values()) {
                    Pattern pattern = Pattern.compile(value.getPattern());
                    Matcher matcher = pattern.matcher(operationString);
                    while(matcher.find()) {
                        operands.add(matcher.group());
                        if(operationString.length()>matcher.end()+1) matcher.region(matcher.end()+1, operationString.length());
                    }
                    if(operands.size()==1) throw new IllegalOperationException("Subtraction of different currencies is illegal!");
                    if(operands.size()==2) {
                        usedCurrency = value;
                        break;
                    }
                }
                Pattern pattern = Pattern.compile("-?\\d+(,\\d+)?");
                Matcher matcher = pattern.matcher(operands.get(0));
                if(matcher.find())
                    operand1 = Currency.parseToDouble(matcher.group());
                matcher = pattern.matcher(operands.get(1));
                if(matcher.find())
                    operand2 = Currency.parseToDouble(matcher.group());
                return usedCurrency.convertNumberIntoCurrency(Currency.parseToString(operand1-operand2));
            }
            case toRubles -> {
                return Currency.russianRuble.convertNumberIntoCurrency(executeExchangeRatesOperation(operationString, Currency.russianRuble));
            }
            case toDollars -> {
                return Currency.dollarUSA.convertNumberIntoCurrency(executeExchangeRatesOperation(operationString, Currency.dollarUSA));
            }
            default -> throw new IllegalOperationException("Unknown operation!");
        }
    }
}
