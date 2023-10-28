package ru.venidiktov.IOandNIO.nio.path_files.walkFileTree.copy;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * В классе Files есть метод walkFileTree который позволяет гулять по дереву директорий
 * Метод walkFileTree принимает два аргумента это начало дерева каталогов и FileVisitor, FileVisitor - это интерфейс
 * реализация которого содержит в себе всю логику обхода дерева директорий.
 * FileVisitor: preVisitDirectory - метод содержит логику выполняющуюся до захода в директорию
 * visitFile - метод содержит логику выполняющуюся при заходе в директорию
 * postVisitDirectory - метод содержит логику выполняющуюся после захода в директорию и обращения ко всем элементам директории
 * visitFileFailed - метод содержит логику выполняющуюся когда файл по каким либо причинам не доступен
 * <p>
 * Метод walkFileTree полезен когда нам например нужно пройтись по директории и ее поддиректориям и удалить все файлы из них
 * <p>
 * В примере ниже выполняем обход дерева c копирование директорий и их содержимого
 */
public class CopyDirectoryUsingWalkFileTreeEx {
    public static void main(String[] args) {
        Path source = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/walkFileTree/copy/X");
        Path destination = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/walkFileTree/copy/COPY_X");

        try {
            Files.walkFileTree(source, new CopyFileVisitor(source, destination));
        } catch (IOException e) {
            System.out.println("Ошибка при обходе дерева директорий " + source);
            e.printStackTrace();
        }
    }

    /**
     * Копирует директории с содержимым
     */
    static class CopyFileVisitor extends SimpleFileVisitor<Path> {
        private Path source;
        private Path destination;

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (!Files.exists(destination)) {
                System.out.println(String.format("Директории %s не было создаем ее", destination));
                Files.createDirectory(destination);
                System.out.println("Создали директорию " + destination);
            }
            Path relativize = source.relativize(dir);
            if (relativize.toString().isBlank()) { // Начальную директорию Х мы уже скопировали выше как COPY_X
                return FileVisitResult.CONTINUE;
            }
            Path newDestination = destination.resolve(relativize);
            System.out.println(String.format("Вставляем директорию %s в директорию назначения %s", relativize, destination));
            Files.copy(dir, newDestination, StandardCopyOption.REPLACE_EXISTING);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path newDestination = destination.resolve(source.relativize(file));
            Files.copy(file, newDestination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(String.format("Скопировали файл %s из директории %s в директорию %s", file.getFileName(), file, newDestination));
            return FileVisitResult.CONTINUE;
        }

        public CopyFileVisitor(Path source, Path destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    /**
     * Удаляет директории с содержимым
     */
    static class DeleteFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            System.out.println("Удалили файл " + file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Files.delete(dir);
            System.out.println("Удалили директорию " + dir);
            return FileVisitResult.CONTINUE;
        }
    }
}
