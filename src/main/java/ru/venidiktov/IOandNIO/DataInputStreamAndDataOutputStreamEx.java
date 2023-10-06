package ru.venidiktov.IOandNIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataInputStream действует противоположным образом - он считывает из потока данные примитивных типов.
 * Соответственно для каждого примитивного типа определен свой метод для считывания
 * DataOutputStream представляет поток вывода и предназначен для записи данных примитивных типов,
 * таких, как int, double и т.д. Для записи каждого из примитивных типов предназначен свой метод
 */
public class DataInputStreamAndDataOutputStreamEx {
    public static void main(String[] args) {
        try (
                DataInputStream inputStream = new DataInputStream(new FileInputStream("src/main/java/ru/venidiktov/IOandNIO/data.bin"));
                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("src/main/java/ru/venidiktov/IOandNIO/data.bin"))
        ) {
            outputStream.writeUTF("Записываем в файл через DataOutputStream");
            outputStream.writeDouble(36.6);
            outputStream.writeChar('F');
            outputStream.writeBoolean(true);

            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readDouble());
            System.out.println(inputStream.readChar());
            System.out.println(inputStream.readBoolean());

        } catch (FileNotFoundException e) {
            System.out.println("Файл data.bin не найден создайте его!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
