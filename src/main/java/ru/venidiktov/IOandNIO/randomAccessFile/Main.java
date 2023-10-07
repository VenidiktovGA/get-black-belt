package ru.venidiktov.IOandNIO.randomAccessFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // инициализируем класс передавая в него путь к файлу
        var worker = new WorkWithFileThrowRandomAccessFile("src/main/java/ru/venidiktov/IOandNIO/randomAccessFile/javaWorld.txt");

        // очищаем содержимое файла
        worker.clearFile();

        // пишем передаваемый текст в файл
        System.out.println("Пишем передаваемый текст в файл");
        worker.write("Hello java world!");
        System.out.println();

        // переходим на указанный символ в файле
        int index = 6;
        System.out.println("Символ в файле по индексу " + index);
        System.out.println(worker.getCharByIndex(index));
        System.out.println();

        // читаем весь файл
        System.out.println("Читаем весь файл");
        System.out.println(worker.readFile());
        System.out.println();

        // читаем файл с указанного символа
        System.out.println("Читаем файл с указанного символа");
        System.out.println(worker.readFrom(6));
        System.out.println();

        // дописываема в конец файла
        String madeBy = "\nWrite by Gennady";
        System.out.println("Дописываема в конец файла: " + madeBy);
        worker.writeToEndFIle(madeBy);
        System.out.println();

        worker.readFile();
    }
}
