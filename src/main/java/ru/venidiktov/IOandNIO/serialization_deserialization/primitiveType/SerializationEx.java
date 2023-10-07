package ru.venidiktov.IOandNIO.serialization_deserialization.primitiveType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Serialization - это механизм позволяющий сохранять объекты в набор байт и сохранять в оперативной памяти, файле, ил каком либо хранилище
 * Deserialization - это процесс восстановления объекта из набора байт
 * Операции serialization и deserialization обычно делаются с помощью ObjectInputStream и ObjectOutputStream
 */
public class SerializationEx {
    public static void main(String[] args) {
        List<String> senior = new ArrayList<>() {{
            add("Ivan");
            add("Maria");
            add("Iosif");
        }};

        List<String> middle = new ArrayList<>() {{
            add("Alex");
            add("Olga");
        }};

        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/employees.bin"));
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/employees.bin"))
        ) {
            System.out.println("Записываем объекты c именами в бинарный файл");
            outputStream.writeObject(senior);
            outputStream.writeObject(middle);
            System.out.println("Записали объекты с именами в бинарный файл");

            System.out.println("Читаем объекты с именами из бинарного файла");
            List<String> inputSeniors = (ArrayList<String>) inputStream.readObject();
            System.out.println("Senior'ы " + inputSeniors); // Если нужно можно делать кастинг к нужному классу
            System.out.println("Middl'ы " + inputStream.readObject());
            System.out.println("Чтение объектов с именами из бинарного файла закончено");

        } catch (FileNotFoundException e) {
            System.out.println("Файл employees.bin не найден, создай его!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("В нашем приложении нет класса для серриализуемого объекта или он не соответствует друг другу");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
