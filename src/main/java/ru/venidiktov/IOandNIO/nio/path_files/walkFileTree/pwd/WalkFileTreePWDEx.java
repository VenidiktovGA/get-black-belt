package ru.venidiktov.IOandNIO.nio.path_files.walkFileTree.pwd;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * В примере ниже выполняем обход дерева просто печатая этапы обхода
 */
public class WalkFileTreePWDEx {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/walkFileTree/pwd/X");
        try {
            Files.walkFileTree(path, new MyFileVisitor());
        } catch (IOException e) {
            System.out.println("Ошибка при обходе дерева директорий " + path);
            e.printStackTrace();
        }
    }


    /**
     * FileVisitResult содержит 4 параметра которые мы можем вернуть нашей программе для того что бы она приняла дальнейшие действия по обработке дерева директорий
     * CONTINUE - продолжать обход по файлам
     * TERMINATE - немедленно прекратить обход
     * SKIP_SUBTREE - в данную директорию заходить не нужно
     * SKIP_SIBLINGS - в данной директории продолжать обход по файлам не нужно (siblings родной брат, файлы и директории в той же директории)
     */
    static class MyFileVisitor implements FileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("Входим в директорию " + dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("Имя файла " + file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("Ошибка при посещении файла " + file.getFileName());
            return FileVisitResult.TERMINATE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("Выходим из директории " + dir);
            return FileVisitResult.CONTINUE;
        }
    }
}
