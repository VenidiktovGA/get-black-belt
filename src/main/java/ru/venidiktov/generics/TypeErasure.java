package ru.venidiktov.generics;

import java.util.ArrayList;
import java.util.List;

/***
 * При внедрении Generics для совместимости java с предыдущими версиями сделали так что
 * generic виден только компилятору JVM никак не видит generic, для JVM везде где используется generic
 * это сырой тип (Object)
 */
public class TypeErasure {
    public static void main(String[] args) {
        //Компилятор видит тип коллекции и не пропустит такое numbers.add("Hello");
        //В JVM это выглядит так List numbers = new ArrayList(); (сырой тип)
        List<Integer> numbers = new ArrayList<>();

        //Только компилятор знает тип коллекции и может выдать ошибку,
        // для jvm это корректно
        numbers.add(22);

        //Компилятор знает тип,
        //для jvm делает так int a = (Integer)numbers.get(0);
        int a = numbers.get(0);
    }

    /***
     * Из за стирания типов сигнатура методов abc() с Generic для JVM будет одинакова и будет ошибка во время выполнения,
     * что бы этого не было ошибка возникает во время компиляции
     */
//    public void abc(Info<String> info) {
//        String s = info.getValue();
//    }
//    public void abc(Info<Integer> info) {
//        Integer i = info.getValue();
//    }

    static class Info <T> { //В jvm вместо T будет Object
        private T value; //В jvm вместо T будет Object

        public Info(T value) {//В jvm вместо T будет Object
            this.value = value;
        }

        public T getValue() {//В jvm вместо T будет Object
            return value;
        }

        @Override
        public String toString() {
            return "{[" + value + "]}"; //У value неявно вызывается toString()
        }
    }

    /***
     * Из за стирания типов, при использовании generic не получится переопределять методы или перегружать методы,
     * так как во время выполнения в jvm эти методы будут иметь одинаковую сигнатуру из за того что информацию о generc (типе) удаляется,
     * что бы этого избежать выводят ошибку во время компиляции
     */
//    static class Parent {
//        public void abc(Info<String> info) {
//            String s = info.getValue();
//        }
//    }
//
//    static class Child extends Parent {
//        @Override
//        public void abc(Info<Integer> info) {
//            Integer i = info.getValue();
//        }
//    }

    /***
     * При использовании wildcard не всегда все типы стираются
     */
    static class MyNumber <T extends Number> { //В jvm вместо T будет Number
        private T value;//В jvm вместо T будет Number

        public MyNumber(T value) {//В jvm вместо T будет Number
            this.value = value;
        }

        public T getValue() {//В jvm вместо T будет Number
            return value;
        }
    }
}
