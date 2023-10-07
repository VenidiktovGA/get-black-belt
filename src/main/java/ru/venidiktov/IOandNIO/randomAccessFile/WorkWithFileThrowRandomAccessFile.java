package ru.venidiktov.IOandNIO.randomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile – класс пакета Java IO API, он позволяет перемещаться по файлу, читать из него или писать в него начиная с любого места.
 * <p>
 * Метод read() читает по одному байту и передвигает курсор
 */
public class WorkWithFileThrowRandomAccessFile {

    private String pathToFile;

    // говорим конструктору проинициализировать путь к файлу
    public WorkWithFileThrowRandomAccessFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    // метод демонстрирует переход на указанный символ
    public char getCharByIndex(int num) {
        // инициализируем класс RandomAccessFile
        // в параметры передаем путь к файлу
        // и модификатор который говорит, что файл откроется только для чтения
        byte character = 'F';
        try (var file = new RandomAccessFile(pathToFile, "r");) {
            file.seek(num); // переходим на num символ
            character = file.readByte(); // получаем текущее состояние курсора в файле
        } catch (IOException e) {
            System.out.println("Не удалось найти символ по указанному индексу");
            e.printStackTrace();
        }
        return (char) character;
    }

    // этот метод читает файл и выводит его содержимое
    public String readFile() throws IOException {
        var content = new StringBuilder();
        try (var file = new RandomAccessFile(pathToFile, "r")) {
            int simbol = -1;
            // побитово читаем символы и плюсуем их в строку
            while ((simbol = file.read()) != -1) {
                content.append((char) simbol);
            }
        }
        return content.toString();
    }

    // читаем файл с определенного символа
    public String readFrom(int numberSymbol) {
        var content = new StringBuilder();
        // открываем файл для чтения
        try (var file = new RandomAccessFile(pathToFile, "r")) {
            file.seek(numberSymbol); // ставим указатель на нужный вам символ
            int simbol = -1;
            while ((simbol = file.read()) != -1) { // побитово читаем и добавляем символы в строку
                content.append((char) simbol);
            }
        } catch (IOException e) {
            System.out.println(String.format("Не удалось прочитать файл начиная с %s символа", numberSymbol));
        }
        return content.toString();
    }

    // дописать в конец файла
    public void writeToEndFIle(String content) {
        try (RandomAccessFile file = new RandomAccessFile(pathToFile, "rw")) {
            file.seek(file.length() - 1); // Перемещаем курсов в конец файла
            file.writeBytes(content);
        } catch (IOException e) {
            System.out.println("Не получилось дописать в конец файла текст: " + content);
            e.printStackTrace();
        }
    }

    // запись в файл
    public void write(String content) throws IOException {
        // открываем файл для записи
        // для этого указываем модификатор rw (read & write)
        // что позволит открыть файл и записать его
        try (var file = new RandomAccessFile(pathToFile, "rw")) {
            file.write(content.getBytes()); // записываем строку переведенную в биты
        }
    }

    // очищаем содержимое файла
    public void clearFile() {
        try (var file = new RandomAccessFile(pathToFile, "rw")) {
            file.setLength(0); // записываем строку переведенную в биты
        } catch (IOException e) {
            System.out.println("Не удалось очистить файл!");
            e.printStackTrace();
        }
    }
}
