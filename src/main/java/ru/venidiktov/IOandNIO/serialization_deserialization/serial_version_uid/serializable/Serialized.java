package ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid.Home2;
import ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid.PersonWithSerialVersionUID;

/**
 * Serialization - это механизм позволяющий сохранять объекты в набор байт и сохранять в оперативной памяти, файле, ил каком либо хранилище
 * Deserialization - это процесс восстановления объекта из набора байт
 * Операции serialization и deserialization обычно делаются с помощью ObjectInputStream и ObjectOutputStream
 * <p>
 * SerialVersionUID — идентификатор класса в языке Java, используемый при сериализации с использованием стадартного алгоритма. Хранится как числовое значение типа long.
 * <p>
 * Сериализуемм объекта Person
 */
public class Serialized {
    public static void main(String[] args) {
        var sumHome = new Home2("Дом солнца");
        var iosif = new PersonWithSerialVersionUID("Iosif", 1, "Alex", sumHome);
        var maria = new PersonWithSerialVersionUID("Maria", 1, "Alex", sumHome);

        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/java/ru/venidiktov/IOandNIO/serialization_deserialization/serial_version_uid/person.bin"));
        ) {

            //Сериализация в файл с помощью класса ObjectOutputStream
            outputStream.writeObject(iosif);
            outputStream.writeObject(maria);

            System.out.println("Serialize: " + "\n" + iosif + "\n" + maria);

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Файл person.bin не найден, создай его!");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
