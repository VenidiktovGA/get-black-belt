package ru.venidiktov.IOandNIO;

import java.io.FileReader;
import java.io.IOException;

/**
 * FileReader используется для чтения данных из файла, файл воспринимается как текстовый
 * 1 Необходимо создать FileWriter
 * 2 Обработать исключение при чтении файла через FileWriter
 * 3 Закрыть FileWriter
 */
public interface FileReaderEx {
    public static void main(String[] args) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/java/ru/venidiktov/IOandNIO/rubai.txt");
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
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
