package ru.venidiktov.IOandNIO.io.serialization_deserialization.serial_version_uid;

import java.io.Serial;

/**
 * Чтобы сериализовать и десериализовать объект он должен имплементировать интерфейс метку Serializable,
 * если не реализовать этот интерфейс получим NotSerializableException
 * <p>
 * SerialVersionUID — идентификатор класса в языке Java, используемый при сериализации с использованием стадартного алгоритма. Хранится как числовое значение типа long.
 * SerialVersionUID лучше задавать явно, так как по умолчанию оно может изменится от многих вещей таких как версия компилятора
 */

/**
 * Чтоб увидеть проблему при серриализации а затем при дессериализации достаточно изменить serialVersionUID ило добавить поле и так далее
 */
public class PersonWithSerialVersionUID implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    private String name;
    //    private String surname; // Добавили поле относительно серриализованного класса, СОВМЕСТИМОЕ ИЗМЕНЕНИЕ
    private transient int countOfNiva; // Модификатор transient говорит что данное поле не нужно серриализовывать
    private String fatherName;
    private Home2 home2; // Класс Home должен имплементировать Serializable

    public PersonWithSerialVersionUID(String name/*, String surname*/, int countOfNiva, String fatherName, Home2 home2) {
        this.name = name;
//        this.surname = surname;
        this.countOfNiva = countOfNiva;
        this.fatherName = fatherName;
        this.home2 = home2;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
//                "surname='" + surname + '\'' +
                ", countOfNiva=" + countOfNiva +
                ", fatherName='" + fatherName + '\'' +
                ", home=" + home2 +
                '}';
    }
}
