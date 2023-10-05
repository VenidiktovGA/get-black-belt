package ru.venidiktov.IOandNIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileInputStream и FileOutputStream наследники классов InputStream и OutputStream реализующие чтение и запись байтов в и из бинарного носителя
 * Классы FileInputStream и FileOutputStream можно предать BufferedInputStream и BufferedOutputStream для достижения буферизации
 */
public class FileInputStreamAndFileOutputStreamEx {
    public static void main(String[] args) {
        try (
                FileInputStream inputStream = new FileInputStream("src/main/java/ru/venidiktov/IOandNIO/javaLogo.png");
                FileOutputStream outputStream = new FileOutputStream("src/main/java/ru/venidiktov/IOandNIO/javaLogo_copy.png")
        ) {
            int data = -1;
            while ((data = inputStream.read()) != -1) {
                outputStream.write(data);
            }
            System.out.println("Бинарный файл скопирован!");
        } catch (FileNotFoundException e) {
            System.out.println("Файл для копирования не найден!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
