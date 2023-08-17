package ru.venidiktov.nested_classes.local_class;

/**
 * Локальные вложенные классы
 */
public class LocalInnerEx2 {
    public static void main(String[] args) {
        /**
         * Локальные вложенные классы можно создавать в блоке кода как мы уже видели в LocalInnerEx1
         */
        class MySum implements Math2 {
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        }

        var mySum = new MySum();
        System.out.println("Сложение: 5 + 8 = " + mySum.doOperation(5, 8));
    }

    interface Math2 {
        int doOperation(int a, int b);
    }
}


