package ru.venidiktov.IOandNIO.io.serialization_deserialization.object;

/**
 * Чтобы сериализовать и десериализовать объект он должен имплементировать интерфейс метку Serializable,
 * если не реализовать этот интерфейс получим NotSerializableException
 */
public class Person1 implements java.io.Serializable {
    private String name;
    private transient int countOfNiva; // Модификатор transient говорит что данное поле не нужно серриализовывать
    private String fatherName;
    private Home1 home; // Класс Home должен имплементировать Serializable

    public Person1(String name, int countOfNiva, String fatherName, Home1 home) {
        this.name = name;
        this.countOfNiva = countOfNiva;
        this.fatherName = fatherName;
        this.home = home;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", countOfNiva=" + countOfNiva +
                ", fatherName='" + fatherName + '\'' +
                ", home=" + home +
                '}';
    }
}