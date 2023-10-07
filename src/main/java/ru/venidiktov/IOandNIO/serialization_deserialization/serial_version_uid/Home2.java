package ru.venidiktov.IOandNIO.serialization_deserialization.serial_version_uid;

/**
 * Чтобы сериализовать и десериализовать объект он должен имплементировать интерфейс метку Serializable,
 * * если не реализовать этот интерфейс получим NotSerializableException
 */
public class Home2 implements java.io.Serializable {
    private String home;

    public Home2(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }
}
