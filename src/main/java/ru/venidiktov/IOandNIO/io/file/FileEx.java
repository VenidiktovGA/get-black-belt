package ru.venidiktov.IOandNIO.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * File определенный в пакете java.io, не работает напрямую с потоками.
 * Его задачей является управление информацией о файлах и каталогах.
 * Хотя на уровне операционной системы файлы и каталоги отличаются, но в Java они описываются одним классом File.
 */
public class FileEx {
    public static void main(String[] args) {
        var existDirectoryPath = "src/main/java/ru/venidiktov/IOandNIO/file";
        var existDirectory = new File(existDirectoryPath);
        var existFilePath = existDirectoryPath + "/FileEx.java";
        var existFile = new File(existFilePath);

        var notExistDirectoryPath = "src/main/java/ru/venidiktov/IOandNIO/file/txt";
        var notExistDirectory = new File(notExistDirectoryPath);
        var notExistFilePath = notExistDirectoryPath + "/file.txt";
        var notExistFile = new File(notExistFilePath);

        System.out.println("ФАЙЛ");
        System.out.println(String.format("Путь %s это путь к файлу = %s", existFilePath, existFile.isFile())); // Если файла нет выдаст false
        System.out.println("Абсолютный путь до файла = " + existFile.getAbsolutePath());
        System.out.println(String.format("Путь %s это абсолютный путь = %s", existFilePath, existFile.isAbsolute()));
        System.out.println(String.format("Файл по пути %s существует = %s", existFilePath, existFile.exists()));
        System.out.println(String.format("Размер файла по пути %s = %s байт", existFilePath, existFile.length()));
        System.out.println(String.format("Файла %s скрытый = %s", existFilePath, existFile.isHidden()));
        System.out.println(String.format("Файл %s можем читать = %s", existFilePath, existFile.canRead()));
        System.out.println(String.format("В файл %s можем писать = %s", existFilePath, existFile.canWrite()));
        System.out.println(String.format("Файл %s можем исполнять = %s", existFilePath, existFile.canExecute()));
        System.out.println();

        System.out.println("ДИРЕКТОРИЯ");
        System.out.println(String.format("Путь %s это путь до директории = %s", existDirectoryPath, existDirectory.isDirectory())); // Если директории нет выдаст false
        System.out.println("Абсолютный путь до директории = " + existDirectory.getAbsolutePath());
        System.out.println(String.format("Путь %s это абсолютный путь = %s", existDirectoryPath, existDirectory.isAbsolute()));
        System.out.println(String.format("Директория по пути %s существует = %s", existDirectoryPath, existDirectory.exists()));
        System.out.println(String.format("Размер всех файлов в директории %s НУЖНО САМОМУ ПИСАТЬ КОД, МЕТОДА НЕТ!", existDirectoryPath));
        System.out.println(String.format("Содержимое директории %s : %s", existDirectoryPath, Arrays.toString(existDirectory.list())));
        System.out.println(String.format("Директория %s скрытая = %s", existDirectoryPath, existDirectory.isHidden()));
        System.out.println();

        System.out.println("Удаляем файл и директорию и создаем их снова");
        //Удалим директорию и файл в ней, если директория не пуста удалить ее не получится
        if (notExistFile.isFile() && notExistFile.exists()) {
            boolean fileIsDelete = notExistFile.delete();
            System.out.println(String.format("Файл по пути %s удалена = %s", notExistFilePath, fileIsDelete));
        }
        if (notExistDirectory.isDirectory() && notExistDirectory.exists()) {
            boolean directoryIdDeleted = notExistDirectory.delete();
            System.out.println(String.format("Директория %s удалена = %s", notExistDirectoryPath, directoryIdDeleted));
        }

        //Создадим директорию и файл в ней
        if (!notExistDirectory.exists()) {
            boolean directoryIsCreated = notExistDirectory.mkdir(); // Создаем директорию если ее нет
            System.out.println(String.format("Директория %s создана = %s", notExistDirectoryPath, directoryIsCreated));
        }
        if (!notExistFile.exists()) {
            try {
                boolean fileIsCreate = notExistFile.createNewFile();
                System.out.println(String.format("Файл по пути %s создана = %s", notExistFilePath, fileIsCreate));
            } catch (IOException e) {
                System.out.println("Не удалось создать файл по пути: " + notExistFilePath);
                e.printStackTrace();
            }
        }
    }
}
