package ru.venidiktov.IOandNIO;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileWriter используется для записи данных в файл, файл воспринимается как текстовый файл
 * 1 Необходимо создать FileWriter
 * 2 Обработать исключение при чтении файла через FileWriter
 * 3 Закрыть FileWriter
 */
public class FileWriterEx {
    public static void main(String[] args) {
        var rubai = "Много лет размышлял я над жизнью земной.\n" +
                "Непонятного нет для меня под луной.\n" +
                "Мне известно, что мне ничего не известно!\n" +
                "Вот последняя правда, открытая мной.";

        /**
         * try with resources компилятором будет переведен в try catch finally где в блоке finally будет закрываться ресурс
         */
        try (FileWriter writer = new FileWriter("src/main/java/ru/venidiktov/IOandNIO/rubai.txt")) {// Если фйла нет он создастся, если есть то перезапишется
            for (int i = 0; i < rubai.length(); i++) {
                /**
                 * метод write() принимает int, следовательно то что мы в него передаем кастится в int
                 */
                writer.write(rubai.charAt(i));
//                writer.write(rubai); // Можно сразу передать строку для записи в файл
            }
            System.out.println("Рубаи в файл записано!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /**
         * Добавление строки в конец файла
         */
        try (FileWriter writer2 = new FileWriter("src/main/java/ru/venidiktov/IOandNIO/rubai.txt", true)) {// Если фйла нет он создастся, если есть то перезапишется
            /**
             * метод write() принимает int, следовательно то что мы в него передаем кастится в int
             */
            writer2.write("\nЭто был рубаи.");
            System.out.println("Добавили строку вконец файла без перезаписи!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
