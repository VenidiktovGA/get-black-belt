package ru.venidiktov.IOandNIO.nio.path_files.filesWriteRead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс Files по мимо создания удаления перемещения файлов и директорий может записывать в файлы и читать из файлов
 */
public class FilesWriteAndReadEx {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/filesWriteRead/hello.txt");
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.out.println("Не удалось удалить файл " + filePath);
            e.printStackTrace();
        }
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + filePath);
            e.printStackTrace();
        }
        var dialog = "G-Hello\nA-Hello\nG-How are you?\nA-good!";
        try {
            Files.write(filePath, dialog.getBytes());
        } catch (IOException e) {
            System.out.println(String.format("Ошибка при записи %s в файл %s", dialog, filePath));
            e.printStackTrace();
        }

        try {
            System.out.println(Files.readAllLines(filePath));
        } catch (IOException e) {
            System.out.println("Ну удалось прочитать файл " + filePath);
        }
    }
}
