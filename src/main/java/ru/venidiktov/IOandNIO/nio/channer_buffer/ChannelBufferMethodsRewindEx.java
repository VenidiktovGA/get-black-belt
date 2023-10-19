package ru.venidiktov.IOandNIO.nio.channer_buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * rewind() сбрасывает poisiton в самое начало буфера а limit не трогает
 * rewind позволяет прочитать уже прочитанные байты в буфере еще раз
 * <p>
 * compact() - перемещает не прочитанные байты в начало буфера, при этом position перемещается на следующую
 * позицию которая следует за перемещенным куском байт (Сдвигает не прочитанные байты) limit при этом не трогается
 * compact позволяет записать в буфер новые данные взамен уже прочитанных без потери еще не прочитанных байт
 * <p>
 * mark() - оставляет метку на данном положении position reset() - перемещает position на ранее оставленную отметку методом mark()
 */
public class ChannelBufferMethodsRewindEx {
    public static void main(String[] args) {
        var filePath = "src/main/java/ru/venidiktov/IOandNIO/nio/channer_buffer/alphabet.txt";
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r"); // В файле латиница в Unicode 1 символ занимает 1 байт
             FileChannel channel = file.getChannel()
        ) {
            System.out.println();
            System.out.println("rewind() ------------------------------------------------------------------------");
            ByteBuffer buffer = ByteBuffer.allocate(5); // Буфер на 5 байт
            System.out.println("1) Буфер на 5 байт создан: " + buffer);
            channel.read(buffer);
            System.out.println("2) Буфер после чтения даннах из канала методом read(): " + buffer);
            buffer.flip(); // Перемещаем position в начало буфера а limit на место position, делаем это чтоб читать из буфера!
            System.out.println("3) Выполнили flip() над буфером для чтения данных из него: " + buffer);
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println("4) Буфер после прочтения 3 байт и до rewind(): " + buffer);
            /**
             * rewind() сбрасывает poisiton в самое начало буфера а limit не трогает
             * rewind позволяет прочитать уже прочитанные байты в буфере еще раз
             */
            buffer.rewind();
            System.out.println("5) Буфер сразу после rewind(): " + buffer);
            System.out.println((char) buffer.get());
            System.out.println();

            System.out.println("compact() ------------------------------------------------------------------------");

            System.out.println("6) Буфер после прочтения из него 1 байта до вызова compact(): " + buffer);
            /**
             * compact() - перемещает не прочитанные байты в начало буфера, при этом position перемещается на следующую
             * позицию которая следует за перемещенным куском байт (Сдвигает не прочитанные байты) limit при этом не трогается
             * compact позволяет записать в буфер новые данные взамен уже прочитанных без потери еще не прочитанных байт
             */
            buffer.compact();
            System.out.println("7) Буфер после вызова compact(): " + buffer);
            channel.read(buffer);
            System.out.println("8) Буфер после чтения даннах из канала методом read(): " + buffer);
            buffer.flip();
            System.out.println("9) Выполнили flip() над буфером для чтения данных из него: " + buffer);
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            System.out.println();

            System.out.println("mark() reset() ------------------------------------------------------------------------");
            buffer.clear();
            System.out.println("10) Буфер сразу после выполнения clear(): " + buffer);
            channel.read(buffer);
            buffer.flip();
            System.out.println("11) Буфер после чтения даннах из канала методом read(): " + buffer);
            System.out.println((char) buffer.get());
            System.out.println("12) Буфер после прочтения 1 байта и до mark(): " + buffer);
            /**
             * mark() - оставляет метку на данном положении position
             */
            buffer.mark();
            System.out.println("13) Буфер сразу после вызова mark(): " + buffer);
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println("14) Буфер после прочтения 2 байт и до reset(): " + buffer);
            /**
             * reset() - перемещает position на ранее оставленную отметку методом mark()
             */
            buffer.reset();
            System.out.println("13) Буфер сразу после вызова reset(): " + buffer);
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println("12) Буфер после прочтения 2 байт: " + buffer);


        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл alphabet.txt надо его создать!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
