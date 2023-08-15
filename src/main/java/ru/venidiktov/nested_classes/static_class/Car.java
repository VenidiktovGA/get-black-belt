package ru.venidiktov.nested_classes.static_class;

public class Car {
    private String color;
    private int doorCount;
    private Engine engine;
    private Signaling signaling;
    private static String BRAND = "VGA";

    public static class Engine {
        private int horsePower;
        public static final String UNIT = "Лошадиные силы";

        public Engine(int horsePower) {
            this.horsePower = horsePower;
        }

        @Override
        public String toString() {
            return "Engine{" +
                    "horsePower=" + horsePower +
                    ", made by=" + Car.BRAND +
                    '}';
        }

        public int getHorsePower() {
            return horsePower;
        }

        public void setHorsePower(int horsePower) {
            this.horsePower = horsePower;
        }
    }

    private static class Signaling {
        private boolean enabled;

        public Signaling(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public String toString() {
            return "Signaling{" +
                    "enabled=" + enabled +
                    '}';
        }

        public boolean isEnabled() {
            return enabled;
        }
    }

    public interface Move {
        String run();

        String stop();
    }

    public Car(String color, int doorCount, Engine engine) {
        this.color = color;
        this.doorCount = doorCount;
        this.engine = engine;
        signaling = new Signaling(true);
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", doorCount=" + doorCount +
                ", engine=" + engine +
                ", signaling=" + signaling +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public boolean getSignaling() {
        return signaling.enabled;
    }

    public String getUnitEngine() {
        return Engine.UNIT;
    }
}
