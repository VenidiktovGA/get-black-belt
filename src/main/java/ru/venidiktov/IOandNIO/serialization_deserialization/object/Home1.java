package ru.venidiktov.IOandNIO.serialization_deserialization.object;

/**
 * Чтобы сериализовать и десериализовать объект он должен имплементировать интерфейс метку Serializable,
 * * если не реализовать этот интерфейс получим NotSerializableException
 */
public class Home1 implements java.io.Serializable {
    private String home;

    public Home1(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }
}