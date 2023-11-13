package ru.venidiktov.scanner;

import java.util.Scanner;

/**
 * Класс Scanner - является "сканнером" который может прочитать отсканировать данные из того или иного источника
 * Scanner может считывать данные из различных источников например: консоль ввода, файл
 * В Scanner есть множество методов для считывания примитивов из источника, nextLine(), nextInt(), nextDouble(), nextBoolean()
 */
public class ScannerStringScanEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("Hello i'm java programmer!");
        String frirstWord = scanner.next();
        System.out.println("Методом next() считали первое слово - " + frirstWord);
        System.out.println();


        /**
         * Чтобы не получить NoSuchElementException при считывании строки используем метод hasNext()
         */
        Scanner scanner2 = new Scanner("Hello i'm java programmer!\nI'm know java!");
        int countString = 1;
        while (scanner2.hasNext()) { // Есть разные вариации методов has...() для разных примитивов, hasInt()
            String s = scanner2.nextLine();
            System.out.println(String.format("Методом nextLine() считали %s строку - %s", countString, s));
            countString++;
        }

        /**
         * Scanner надо закрывать, он так же занимает ресурсы как и например подключение к БД или чтение файла
         */
        scanner.close();
        scanner2.close();
    }
}
