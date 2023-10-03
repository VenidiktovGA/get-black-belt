package ru.venidiktov.IOandNIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * BufferedReader - считывает данные из источника пачкой (зависит от размера буфера) и помещает
 * данные в буфер и от туда выдает потребителю
 * BufferedWriter - записывает данные в источник пачкой используя буфер, сначала накапливая данные в буфере а после записывая их в источник
 */
public class BufferedReaderAndBufferedWriterEx {
    public static void main(String[] args) {
        /**
         * try with resources компилятором будет переведен в try catch finally где в блоке finally будет закрываться ресурс
         */
        try (
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ru/venidiktov/IOandNIO/rubai.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/ru/venidiktov/IOandNIO/rubai_copy.txt"));
        ) {
            String line; // В BufferedReader есть метод для чтения целой строки
            while ((line = reader.readLine()) != null) { // Метод read() читает данные из файла посимвольно и возвращает символ, если вернулось -1 то достигнут конец файла
                System.out.println(line);
                writer.write(line); // В BufferedWriter есть метод для записи целой строки (Спец символы не копируются!)
                writer.write('\n');
            }
            System.out.println();
            System.out.println();
            System.out.println("Файл полностью прочитан!");
        } catch (IOException e) {
            System.out.println("Файл не найден, сгенерируй его с помощью класса BufferedWriterEx.java");
            e.printStackTrace();
        }
    }
}
