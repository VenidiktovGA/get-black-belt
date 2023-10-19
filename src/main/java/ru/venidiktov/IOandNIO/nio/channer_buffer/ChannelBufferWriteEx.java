package ru.venidiktov.IOandNIO.nio.channer_buffer;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Buffer - некоторая область памяти выступающая в роли промежуточного хранилища набора байт,
 * в стандартной библиотеке буфер представлен абстрактным классом Buffer и множеством его наследников.
 * Channel - определяет каналы, которые представляют соединения с объектами,
 * которые способны к выполнению операций ввода-вывода, таковы как файлы и сокеты.
 * <p>
 * В программе ниже у нас есть строка которую мы преобразовываем в массив байт в далее массив байт помещает в
 * InputStream за тем чтобы считывать из него количество байт равное размеру буфера и далее записывать байты из буфера.
 * Можно просто создать буфер размером со строку и сразу записать всю строку в файл, но это мне кажется не верно,
 * чем больше файл тем больше будет буфер, причем буфер только на 1 раз!
 */
public class ChannelBufferWriteEx {
    public static void main(String[] args) {
        var filePath = "src/main/java/ru/venidiktov/IOandNIO/nio/channer_buffer/hello.txt";
//        byte [] byteDate = "Hello. Привет!".getBytes(StandardCharsets.UTF_8); // В такой кодировке вся строка занимает 20 байт!
        byte[] byteDate = "Hello. Привет!".getBytes(); // В стандартной Unicode кодировке вся строка занимает 40 байт! Кирилические символы по 2 байта
        try (
                OutputStream outputStream = new FileOutputStream(filePath);
                InputStream inputStream = new ByteArrayInputStream(byteDate);
                WritableByteChannel writeChannel = Channels.newChannel(outputStream);
                ReadableByteChannel readChannel = Channels.newChannel(inputStream)
        ) {
            int bufferSize = 10;
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize); // Создали буфер на 10 байт, изначально заполнен нулями
//            buffer.put(byteDate); // Можно поместить байты в буфер и далее их считать от туда, но если байт больше чем размер буфера будет Exception

            int bytesRead = -1;
            while ((bytesRead = readChannel.read(buffer)) > 0) { // channel.read() возвращает количество байт помещенных в буфер из источника
                System.out.println("---- BytesRead: " + bytesRead + " ----");
                buffer.flip(); // Перемещаем position в начало буфера а limit на место position, делаем это чтоб читать из буфера!
                while (buffer.hasRemaining()) { // Проверяем есть ли что читать, есть что читать пока position != limit
                    System.out.println("---- Write bytes: " + bytesRead + " ----");
                    writeChannel.write(buffer);
                }
                buffer.clear(); // position в самое начало limit в самый конец, делаем это чтоб записать следующий 10 байт из файла!
            }
        } catch (FileNotFoundException e) {
            System.out.println("Если файла нет он должен создастся, данного исключения не должно быть!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
