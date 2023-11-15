package ru.venidiktov.reflection.calculator;


public class Calculator {
    void sum(int a, int b) {
        int result = a + b;
        System.out.println(String.format("Сумма числа %s и числа %s = %s", a, b, result));
    }

    void subtraction(int a, int b) {
        int result = a - b;
        System.out.println(String.format("Разница числа %s и числа %s = %s", a, b, result));
    }

    void multiplication(int a, int b) {
        int result = a * b;
        System.out.println(String.format("Произведение числа %s и числа %s = %s", a, b, result));
    }

    void division(int a, int b) {
        int result = a / b;
        System.out.println(String.format("Частное числа %s и числа %s = %s", a, b, result));
    }
}
