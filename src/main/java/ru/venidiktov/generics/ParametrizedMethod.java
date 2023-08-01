package ru.venidiktov.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризированные методы удобны тем что их можно вызывать для большого числа типов,
 * если например для набора типов нужен метод с одинаковой логикой
 */
public class ParametrizedMethod {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(22);
        numbers.add(11);
        numbers.add(12);
        int secondNumber = GenMethod.getSecondElement(numbers);
        System.out.println("Второй элемент в коллекции = " + secondNumber);

        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Jordan 23");
        words.add("");
        String wordsNumber = GenMethod.getSecondElement(words);
        System.out.println("Второй элемент в коллекции = " + wordsNumber);
    }

    static class GenMethod {
        //<T> говорит о том что в методе мы используем Generic, вот тут List<T> и в возвращаемом типе T
        //<T> нужно писать так как java не знает что мы хотим параметризированный метод, в параметризированном классе такое указывать не нужно
        // T это тип возвращаемого значения
        public static <T> T getSecondElement(List<T> list) {
            return list.get(1);
        }
    }
}
