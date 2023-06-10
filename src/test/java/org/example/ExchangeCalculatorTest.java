package org.example;

import org.example.converter.implementations.Converter;
import org.example.converter.interfaces.IConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeCalculatorTest {

    static IConverter converter;

    @BeforeAll
    static void beforeAll() {
        converter = new Converter();
    }

    @Test
    public void toRublesSimpleOperationTest() {
        assertEquals("60,00р", converter.performOperation("toRubles($1,00  )"));
        assertEquals("90,00р", converter.performOperation(" toRubles($1,50) "));
    }

    @Test
    public void toDollarsSimpleOperationTest() {
        assertEquals("$1,00", converter.performOperation("toDollars(  60,00р)"));
        assertEquals("$1,49", converter.performOperation(" toDollars(90,00р  )"));
    }

    @Test
    public void rublesSummarySimpleOperationTest() {
        assertEquals("150,00р", converter.performOperation("60,00р + 90,00р"));
        assertEquals("4,22р", converter.performOperation("  1,25р +   2,97р"));
        assertEquals("4,22р", converter.performOperation(" ( ( (1,25р +   2,97р) ) ) "));
    }

    @Test
    public void dollarsSummarySimpleOperationTest() {
        assertEquals("$2,00", converter.performOperation("$1,00 + $1,00"));
        assertEquals("$7,85", converter.performOperation("  $1,25 +   $6,60"));
        assertEquals("$7,85", converter.performOperation(" ( ( ($1,25 +   $6,60) ) ) "));
    }

    @Test
    public void rublesExpressionOperationsTest() {
        assertEquals("0,01р", converter.performOperation("60,00р + 90,00р - 149,99р"));
        assertEquals("150,00р", converter.performOperation("60,00р  +  toRubles($1,50)"));
    }

    @Test
    public void dollarsExpressionOperationsTest() {
        assertEquals("$0,50", converter.performOperation("$1,50 + $1,50 - $2,50"));
        assertEquals("$-0,49", converter.performOperation("$1,50  -  toDollars(120,00р)"));
    }

    @Test
    public void innerOperationsTest() {
        assertEquals("$3,98", converter.performOperation("toDollars(120,00р + 120,00р)"));
        assertEquals("$1,49", converter.performOperation("toDollars(toRubles($1,50))"));
        assertEquals("$3,98", converter.performOperation("toDollars(toRubles($1,50  + $2,50))"));
    }
}