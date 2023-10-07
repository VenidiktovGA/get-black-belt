package ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid.deserializer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid.PersonWithSerialVersionUID;

/**
 * Serialization - это механизм позволяющий сохранять объекты в набор байт и сохранять в оперативной памяти, файле, ил каком либо хранилище
 * Deserialization - это процесс восстановления объекта из набора байт
 * Операции serialization и deserialization обычно делаются с помощью ObjectInputStream и ObjectOutputStream
 * <p>
 * SerialVersionUID — идентификатор класса в языке Java, используемый при сериализации с использованием стадартного алгоритма. Хранится как числовое значение типа long.
 * <p>
 * Десериализуемм объекта Person
 */
public class Deserialized {
    public static void main(String[] args) {
        try (
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/serial_version_uid/person.bin"))
        ) {
            // Восcтановление из файла с помощью класса ObjectInputStream
            // После восстановления объекты Home обоих объектов Person ссылаются на один и тот же объект
            PersonWithSerialVersionUID iosifRestored = (PersonWithSerialVersionUID) inputStream.readObject();
            PersonWithSerialVersionUID mariaRestored = (PersonWithSerialVersionUID) inputStream.readObject();

            System.out.println("After Restored:");
            System.out.println("Поле countOfNiva помечено transient поэтому оно не серриализовалось и при дессериализации имеет значение по умолчанию");
            System.out.println(iosifRestored);
            System.out.println(mariaRestored);

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Файл person.bin не найден, создай его!");
        } catch (ClassNotFoundException e) {
            System.out.println("В нашем приложении нет класса для серриализуемого объекта или он не соответствует друг другу");
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
