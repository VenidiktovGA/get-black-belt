package ru.venidiktov.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс Scanner - является "сканнером" который может прочитать отсканировать данные из того или иного источника
 * Scanner может считывать данные из различных источников например: консоль ввода, файл
 * В Scanner есть множество методов для считывания примитивов из источника, nextLine(), nextInt(), nextDouble(), nextBoolean()
 */
public class ScannerFileScanEx {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>();
        try (Scanner fileScanner = new Scanner(new FileReader(new File("src/main/java/ru/venidiktov/scanner/Jackie Chan.txt")))) {
            fileScanner.useDelimiter("\\W"); // Разделитель строк это любой символ кроме цифры буквы или _
            while (fileScanner.hasNext()) {
                String word = fileScanner.next(); // Читаем отдельное слово
                words.add(word);
            }
            System.out.println("Уникальные слова и символы (кроме _) в тексте: " + words);
            System.out.println(words.contains(","));
        } catch (FileNotFoundException e) {
            System.out.println("Не найдет файл Jackie Chan.txt");
            e.printStackTrace();
        }
    }
}
