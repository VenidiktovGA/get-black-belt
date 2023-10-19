package ru.venidiktov.IOandNIO.nio.channer_buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Buffer - некоторая область памяти выступающая в роли промежуточного хранилища набора байт,
 * в стандартной библиотеке буфер представлен абстрактным классом Buffer и множеством его наследников.
 * Channel - определяет каналы, которые представляют соединения с объектами,
 * которые способны к выполнению операций ввода-вывода, таковы как файлы и сокеты.
 * <p>
 * Ниже мы читаем строку побайтово и выводим байты на консоль, так как некоторые символы в Unicode состоят из нескольких
 * байт они будут отображены на консоли не корректно!
 */
public class ChannelBufferReadEx {
    public static void main(String[] args) {
        var filePath = "src/main/java/ru/venidiktov/IOandNIO/nio/channer_buffer/fish.txt";
        try (
                RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw");
                FileChannel channel = accessFile.getChannel() // Получаем канал из связи с нашим файлом
        ) {
            System.out.println("Читаем строку JP日本-日本Я, некоторые символы занимают более 1 байта!!!");
            System.out.println("J,P,- : имеют размер 1 байт");
            System.out.println("Я- : имеет размер 2 байт, их не удастся вывести на консоль через чтение байт вот так (char)byteRead");
            System.out.println("日,本,日,本 : имеют размер 3 байт, их не удастся вывести на консоль через чтение байт вот так (char)byteRead");
            int bufferSize = 10;
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize); // Создали буфер на 10 байт, изначально заполнен нулями
            int bytesRead = -1;
            System.out.println(String.format("Начинаем читать данные из буфера размером %s байт", bufferSize));
            List<Byte> list = new ArrayList<>();
            while ((bytesRead = channel.read(buffer)) > -1) { // channel.read() возвращает количество байт помещенных в буфер из источника
                System.out.println("---- BytesRead: " + bytesRead + " ----");
                buffer.flip(); // Перемещаем position в начало буфера а limit на место position, делаем это чтоб читать из буфера!
                while (buffer.hasRemaining()) { // Проверяем есть ли что читать, есть что читать пока position != limit
                    byte b = buffer.get();
                    list.add(b);
                    int charCode = Byte.toUnsignedInt(b);
                    System.out.println((char) charCode + " --> " + charCode);
                }
                buffer.clear(); // position в самое начало limit в самый конец, делаем это чтоб записать следующий 10 байт из файла!
            }
            System.out.println();
            /**
             * Выведем массив байт как строку с учетом кодировки, тут символа определятся нормально!
             */
            byte[] ff = new byte[list.size()];
            IntStream.range(0, list.size()).forEach(i -> ff[i] = list.get(i));
            System.out.println("Исходная строка собранная из последовательности байт");
            System.out.println(new String(ff, Charset.defaultCharset()));
        } catch (FileNotFoundException e) {
            System.out.println("Файл fish.txt не найден!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
