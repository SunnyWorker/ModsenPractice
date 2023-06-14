package org.example;

import org.example.converter.implementations.Converter;
import org.example.converter.interfaces.IConverter;

import java.util.Scanner;

public class Launcher {
    public static final String entryMessage = "Enter request: ";
    public static final String answerMessage = "Your answer: ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IConverter converter = new Converter();
        while (true) {
            System.out.println(entryMessage);
            System.out.println(answerMessage + converter.performOperation(sc.nextLine()));
        }
    }
}