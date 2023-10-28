package ru.venidiktov.IOandNIO.nio.path_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Класс Files призван заменить (совместно с интерфейсом Path) устаревший класс java.io.File
 * Files - сосредоточен на управлении файлами и директориями. Используя статические методы Files,
 * мы можем создавать, удалять и перемещать файлы и директории.
 */
public class FilesEx {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/file.txt");
        Path direcrotyPath = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/sub_directory");

        try {
            if (!Files.exists(filePath)) {
                System.out.println(String.format("Файла %s нет создаем его", filePath));
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + filePath);
            e.printStackTrace();
        }

        try {

            if (!Files.exists(direcrotyPath)) {
                System.out.println(String.format("Директории %s нет создаем ее", direcrotyPath));
                Files.createDirectory(direcrotyPath);
            }
        } catch (IOException e) {
            System.out.println("Не удалось создать директорию " + direcrotyPath);
            e.printStackTrace();
        }

        System.out.println("У нас есть доступ на чтение к файлу = " + Files.isReadable(filePath));
        System.out.println("У нас есть доступ на запись к файлу = " + Files.isWritable(filePath));
        System.out.println("У нас есть доступ на исполнение к файлу = " + Files.isExecutable(filePath));

        var anotherPathFile = Paths.get("D:\\Crocodil\\Programming\\Java\\Projects\\JAVA - Poluchi CHernyiy Poyas\\get-black-belt\\src\\main\\java\\ru\\venidiktov\\IOandNIO\\nio\\path_files\\file.txt");
        try {
            System.out.println(String.format("Путь %s\nи путь %s\nссылаются на один и тот же файл = %s", anotherPathFile, filePath, Files.isSameFile(anotherPathFile, filePath)));
        } catch (IOException e) {
            System.out.println("Не могу сравнить пути!");
            e.printStackTrace();
        }

        try {
            System.out.println(String.format("Размер файла %s = %s байт", filePath, Files.size(filePath)));
        } catch (IOException e) {
            System.out.println("Не получилось узнать размер файла!");
            e.printStackTrace();
        }

        try {
            System.out.println(String.format("Атрибут \"creationTime\" файла %s = %s", filePath, Files.getAttribute(filePath, "creationTime")));
        } catch (IOException e) {
            System.out.println("Не получилось узнать дату создания файла!");
            e.printStackTrace();
        }

        try {
            System.out.println("Все атрибуты файла: " + Files.readAttributes(filePath, "*"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * copy() - копирует существующий файл
         * StandardCopyOption.REPLACE_EXISTING - если такой файл уже есть заменить его
         */
        var copyFileName = "file_copy.txt";
        try {
            System.out.println(String.format("Скопировали файл %s в папку %s c именем %s, с заменой если он уже есть", filePath, direcrotyPath, copyFileName));
            Files.copy(filePath, direcrotyPath.resolve(copyFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Не удалось скопировать файл!");
            e.printStackTrace();
        }
        var subSubDirectory = "sub_sub_directory";
        try {
            if (!Files.exists(direcrotyPath.resolve(subSubDirectory))) {
                System.out.println(String.format("Создали под директорию %s в директории %s", subSubDirectory, direcrotyPath));
                Files.copy(direcrotyPath, direcrotyPath.resolve(subSubDirectory)); // Существующая директория копируется без содержимого!
            }
        } catch (IOException e) {
            System.out.println("Не удалось скопировать директорию!");
            e.printStackTrace();
        }

        /**
         * delete() - удаляет файл или директорию
         * Если папка не пуста ее не удалить, будет Exception!
         */
        try {
            if (Files.exists(direcrotyPath.resolve(subSubDirectory).resolve(copyFileName))) {
                Files.delete(direcrotyPath.resolve(subSubDirectory).resolve(copyFileName));
            }
        } catch (IOException e) {
            System.out.printf("Не удалось удалить файл " + direcrotyPath.resolve(subSubDirectory).resolve(copyFileName));
        }

        /**
         * move() - перемещает файл с одной директории в другую
         * С помощью метода move() можно и переименовать файл (Метода rename нет) просто переместив файл туда же где он был с другим именем
         */
        try {
            if (Files.exists(direcrotyPath) && Files.exists(direcrotyPath.resolve(copyFileName))) {
                // Перемещаем файл!
                Files.move(direcrotyPath.resolve(copyFileName), direcrotyPath.resolve(subSubDirectory).resolve(copyFileName));
                if (!Files.exists(direcrotyPath.resolve(subSubDirectory).resolve("file.txt"))) {
                    // Переименовываем файл!
                    Files.move(direcrotyPath.resolve(subSubDirectory).resolve(copyFileName), direcrotyPath.resolve(subSubDirectory).resolve("file.txt"));
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось переместить файл!");
            e.printStackTrace();
        }
    }
}
