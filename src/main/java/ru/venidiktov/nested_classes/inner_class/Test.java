package ru.venidiktov.nested_classes.inner_class;

/**
 * Вложенный класс можно использовать только с родительским классом!!!
 * Вложенные классы можно делать final, private, abstract по сути это обычные классы!
 */
public class Test {
    public static void main(String[] args) {

        /**
         * 1 Вариант создания вложенного класса -> в конструкторе внешнего класса
         * Экземпляр вложенного класса нельзя создать вне внешнего класса (у статического можно)
         */
        var car = new Car("red", 4, 125);
        System.out.println(car);

//        Car.Engine engine = new Car.Engine(22);
        System.out.println("Создали машину сразу с мотором = " + car.getEngine());

        /**
         * 2 Способ создания вложено класса -> как объект внешнего класса а потом засетить во внешний класс
         * Car.Engine engine2 = car.new Engine(150);
         */
        var car2 = new Car("blue", 3);
        Car.Engine engine2 = car.new Engine(150);
        car2.setEngine(engine2);
        System.out.println("Сначала создали машину потом добавили мотор = " + car2);

        /**
         * 3 Способ создать вложенный класс,
         * но тут потеряется ссылка на машину
         */
        var engine3 = new Car("black", 4).new Engine(200);
        System.out.println("Создали двигатель а машину потеряли = " + engine3);

        /**
         * Приватный (private) вложенный класс во вне нельзя увидеть, он доступен только в родительском классе
         * Можно получить к нему доступ через методы внешнего класса
         */
//        Car.Signaling signaling = new Car.Signaling();
        System.out.println("Signaling is work = " + car.getSignaling());

        /**
         * Вложенный класс может содержать обычные и статические поля
         */
        System.out.println("В чем измеряется мощность мотора? = " + Car.Engine.UNIT);

        /**
         * Вложенный класс может обращаться к статическим полям родительского класса
         */
        System.out.println("Какой марки авто смотри в описание -> " + car.getEngine());

        /**
         * Родительский класс имеет доступ к статическим полям вложенного класса
         */
        System.out.println("В машине двигатель на " + car.getUnitEngine());

        /**
         * Вложенный класс может содержать статические переменные
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
     * Вложенные классы это обычные классы с ними можно делать все то же что и с обычными,
     * Наследоваться от вложенных классов нельзя (от статических можно)!
     */
//    private class Z extends Car.Engine {
//        Z() {
//            super(300);
//        }
//    }
}
