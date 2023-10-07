package ru.venidiktov.IOandNIO.serialization_deserialization.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialization - это механизм позволяющий сохранять объекты в набор байт и сохранять в оперативной памяти, файле, ил каком либо хранилище
 * Deserialization - это процесс восстановления объекта из набора байт
 * Операции serialization и deserialization обычно делаются с помощью ObjectInputStream и ObjectOutputStream
 * <p>
 * Сериализуемм и Десериализуем объекты Person
 */
public class SerializeAndDeserialize {
    public static void main(String[] args) {
        var sumHome = new Home1("Дом солнца");
        var iosif = new Person1("Iosif", 1, "Alex", sumHome);
        var maria = new Person1("Maria", 1, "Alex", sumHome);

        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/object/person.bin"));
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/object/person.bin"))
        ) {

            //Сериализация в файл с помощью класса ObjectOutputStream
            outputStream.writeObject(iosif);
            outputStream.writeObject(maria);

            // Восcтановление из файла с помощью класса ObjectInputStream
            // После восстановления объекты Home обоих объектов Person ссылаются на один и тот же объект
            Person1 iosifRestored = (Person1) inputStream.readObject();
            Person1 mariaRestored = (Person1) inputStream.readObject();

            System.out.println("Before Serialize: " + "\n" + iosif + "\n" + maria);
            System.out.println("After Restored:");
            System.out.println("Поле countOfNiva помечено transient поэтому оно не серриализовалось и при дессериализации имеет значение по умолчанию");
            System.out.println(iosifRestored);
            System.out.println(mariaRestored);

        } catch (FileNotFoundException e) {
            System.out.println("Файл person.bin не найден, создай его!");
        } catch (ClassNotFoundException e) {
            System.out.println("В нашем приложении нет класса для серриализуемого объекта или он не соответствует друг другу");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
