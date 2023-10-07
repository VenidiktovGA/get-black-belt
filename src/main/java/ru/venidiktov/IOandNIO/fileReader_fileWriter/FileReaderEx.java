package ru.venidiktov.IOandNIO.fileReader_fileWriter;

import java.io.FileReader;
import java.io.IOException;

/**
 * FileReader используется для чтения данных из файла, файл воспринимается как текстовый
 * 1 Необходимо создать FileWriter
 * 2 Обработать исключение при чтении файла через FileWriter
 * 3 Закрыть FileWriter
 */
public class FileReaderEx {
    public static void main(String[] args) {
        /**
         * try with resources компилятором будет переведен в try catch finally где в блоке finally будет закрываться ресурс
         */
        try (FileReader reader = new FileReader("src/main/java/ru/venidiktov/IOandNIO/fileReader_fileWriter/rubai.txt")) {
            int character;
            while ((character = reader.read()) != -1) { // Метод read() читает данные из файла посимвольно и возвращает символ, если вернулось -1 то достигнут конец файла
                System.out.print((char) character);
            }
            System.out.println();
            System.out.println();
            System.out.println("Файл полностью прочитан!");
        } catch (IOException e) {
            System.out.println("Файл не найден, сгенерируй его с помощью класса FileWriterEx.java");
            e.printStackTrace();
        }
    }
}
