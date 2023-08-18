package ru.venidiktov.nested_classes.anonymous_class;

/**
 * Анонимный класс
 * Анонимный класс не имеет имени
 * Создание анонимного класса делается с его объявлением
 */
public class AnonymousClass {

    private static String MADE_BY = "Venidiktov. G.A.";

    public static void main(String[] args) {

        /**
         * Создаем анонимный класс который имплементирует интерфейс
         */
        MyMath myMath = new MyMath() {
            private int field = 22; // Поле анонимного класса!

            // Метод можно вызвать только внутри анонимного класса!
            public void hello() {
                System.out.println("Привет я анонимный класс, моя ссылка = " + this);
                System.out.println("Анонимный класс имеет доступ до private полей внешнего класса!");
                System.out.println("Код написал " + MADE_BY);
            }

            @Override
            public int doOperation(int a, int b) {
                hello();
                return a + b;
            }
        };

        System.out.println("Результат 3 + 6 = " + myMath.doOperation(3, 6));

        /**
         * Анонимный класс может расширять другой класс
         */
        var myPrint = new MyPrint() {
        };
        myPrint.hello();

    }

    interface MyMath {
        int doOperation(int a, int b);
    }

    abstract static class MyPrint {
        public void hello() {
            System.out.println("Привет от класса наследуемого MyPrint");
        }
    }
}
