package ru.venidiktov.IOandNIO.nio.path_files;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Интерфейс Path появились в замен устаревшего класса java.io.File и призван упрастить работу с путями
 * Класс Paths это класс возвращающий Path, по сути единственная задача класса Paths это из переданной строки или URI вернуть Path
 */
public class PathEx {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/java/ru/venidiktov/IOandNIO/nio/path_files/file.txt");
        var absolutPath = "D:\\Crocodil\\Programming\\Java\\Projects\\JAVA - Poluchi CHernyiy Poyas\\get-black-belt\\src\\main\\java\\ru\\venidiktov\\IOandNIO\\nio\\path_files";
        Path direcrotyPath = Paths.get(absolutPath);

        System.out.println("ФАЙЛ");
        System.out.println("Имя файла = " + filePath.getFileName());
        System.out.println("Родительская директория файла = " + filePath.getParent());
        System.out.println("Корневая директория файла = " + filePath.getRoot()); // Так как путь относительный то будет null
        System.out.println("Путь до файла абсолютный? = " + filePath.isAbsolute());
        System.out.println("Абсолютный путь к фалу = " + filePath.toAbsolutePath());
        System.out.println("Корневая директория файла относительно абсолютного пути = " + filePath.toAbsolutePath().getRoot());
        System.out.println("Разрешим путь С:/ и путь до нашего файла = " + Paths.get("C:/").resolve(filePath));
        System.out.println();

        System.out.println("ДИРЕКТОРИЯ");
        System.out.println("Имя директории = " + direcrotyPath.getFileName());
        System.out.println("Родительская директория = " + direcrotyPath.getParent());
        System.out.println("Корневая директория = " + direcrotyPath.getRoot());
        System.out.println("Путь до директории абсолютный = " + direcrotyPath.isAbsolute());
        System.out.println("Абсолютный путь к директории = " + filePath.toAbsolutePath());
        System.out.println("Разрешим путь до нашей директории и файла doom.txt = " + direcrotyPath.resolve(Paths.get("doom.txt")));
        var anotherPath = Paths.get("D:\\Crocodil\\Programming\\Java\\Projects\\JAVA - Poluchi CHernyiy Poyas\\get-black-belt\\src\\main\\java\\ru\\venidiktov\\IOandNIO\\io\\file");
        System.out.println(String.format("Путь до нашей директории относительно пути %s\nи но будет = %s", anotherPath, direcrotyPath.relativize(anotherPath)));
    }
}
