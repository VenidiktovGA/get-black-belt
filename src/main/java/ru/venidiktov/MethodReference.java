package ru.venidiktov;

import java.util.List;

/**
 * Ссылки на методы появились в java 8 они еще компактнее чем Lambda выражения
 */
public class MethodReference {
    public static void main(String[] args) {
        List<String> list = List.of("Apple", "Orange");

        /**
         * Вызов метода экземпляра (println) у экземпляра (PrintStream out),
         * параметр передается как явный аргумент в метод (println)
         */
        list.stream().forEach(System.out::println);

        /**
         * Вызов статического метода,
         * параметр передается как явный аргумент в метод
         */
        list.stream().forEach(Utils::method);
    }

    static class Utils {
        public static void method(String s) {
            System.out.println("В мой метод пришла строка = " + s);
        }
    }
}
