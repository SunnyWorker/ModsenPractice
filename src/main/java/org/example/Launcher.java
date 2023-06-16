package org.example;

import org.example.converter.implementations.Converter;
import org.example.converter.interfaces.IConverter;

import java.util.Scanner;

public class Launcher {
    public static final String ENTRY_MESSAGE = "Enter request: ";
    public static final String ANSWER_MESSAGE = "Your answer: ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IConverter converter = new Converter();
        while (true) {
            System.out.println(ENTRY_MESSAGE);
            System.out.println(ANSWER_MESSAGE + converter.performOperation(sc.nextLine()));
        }
    }
}