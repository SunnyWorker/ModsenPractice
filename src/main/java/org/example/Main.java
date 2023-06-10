package org.example;

import org.example.converter.implementations.Converter;
import org.example.converter.interfaces.IConverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IConverter converter = new Converter();
        while(true) {
            System.out.println("Enter request: ");
            System.out.println("Your answer: " + converter.performOperation(sc.nextLine()));
        }
    }
}