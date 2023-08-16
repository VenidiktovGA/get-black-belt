package ru.venidiktov.nested_classes.static_class;

/**
 * Статический вложенный класс можно использовать без родительского класса
 * Статические классы можно делать final, private, abstract по сути это обычные классы!
 */
public class Test {
    public static void main(String[] args) {

        /**
         * Создаем экземпляр вложенного статичного класса,
         * если вложенный клас был private то его так не получить, он виден только внутри родительского класса
         */
        Car.Engine engine = new Car.Engine(22);
        System.out.println(engine);

        var car = new Car("red", 4, engine);
        System.out.println(car);

        /**
         * Приватный (private) статический вложенный класс во вне нельзя, он доступен только в родительском классе
         */
//        Car.Signaling signaling = new Car.Signaling();
        System.out.println("Signaling is work = " + car.getSignaling());

        /**
         * Статический вложенный класс может содержать обычные и статические поля
         */
        System.out.println("В чем измеряется мощность мотора? = " + Car.Engine.UNIT);

        /**
         * Статический вложенный класс может обращаться к статическим полям родительского класса
         */
        System.out.println("Какой марки авто смотри в описание -> " + engine);

        /**
         * Родительский класс имеет доступ к статическим полям статического вложенного класса
         */
        System.out.println("В машине двигатель на " + car.getUnitEngine());

        /**
         * Статический вложенный класс может содержать статические переменные
         */
        System.out.println("Сколько было создано двигателей = " + Car.Engine.getCountEngines());

        /**
         * Вложенными могут быть так же и интерфейсы
         */
        Car.Move move = new Car.Move() { // Это уже анонимный класс, но это другая история
            @Override
            public String run() {
                return "Поехали!";
            }

            @Override
            public String stop() {
                return "Остановились!";
            }
        };

    }

    /**
     * Статические вложенные классы это обычные классы с ними можно делать все то же что и с обычными,
     * например наследоваться от них
     */
    private class Z extends Car.Engine {
        Z() {
            super(300);
        }
    }
}
