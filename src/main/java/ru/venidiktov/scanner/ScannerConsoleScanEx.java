package ru.venidiktov.scanner;

import java.util.Scanner;

/**
 * Класс Scanner - является "сканнером" который может прочитать отсканировать данные из того или иного источника
 * Scanner может считывать данные из различных источников например: консоль ввода, файл
 * В Scanner есть множество методов для считывания примитивов из источника, nextLine(), nextInt(), nextDouble(), nextBoolean()
 */
public class ScannerConsoleScanEx {
    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in); //System.in - это объект InputStream
        System.out.println("Введите 2 числа");
        int i1 = consoleScanner.nextInt();
        int i2 = consoleScanner.nextInt();
        System.out.println("Первое число которое вы ввели - " + i1);
        System.out.println("Второе число которое вы ввели - " + i2);
        System.out.println();

        System.out.println("Введите ваше имя");
        /**
         * Лишнее считывание строки consoleScanner.nextLine(); нужно чтоб считать строку после считывания чисел,
         * так как при считывании Int методом nextInt() не читает символ новой строки
         * этот символ читает метод nextLine() идущий после и завершает чтение так как символ новой строки означает закончить сканирование
         */
        consoleScanner.nextLine(); // Прочитает символ конца строки не прочитанный последним методом nextInt()
        String name = consoleScanner.nextLine(); // Ждет ввода строки с символом ее окончания
        System.out.println("Привет " + name + "!");
        System.out.println();

        /**
         * метод next() - считывает символы из строки до первого пробела
         */
        System.out.println("Метод next() - считывает символы из строки до первого пробела");
        System.out.println("Введите строку из нескольких слов с пробелами");
        String firstWord = consoleScanner.next();
        System.out.println("Метод next() считал слово - " + firstWord);

        /**
         * Scanner надо закрывать, он так же занимает ресурсы как и например подключение к БД или чтение файла
         */
        consoleScanner.close();
    }
}
