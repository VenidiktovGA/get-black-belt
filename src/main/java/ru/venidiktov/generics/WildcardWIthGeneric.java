package ru.venidiktov.generics;

import java.util.ArrayList;
import java.util.List;

/***
 * Wildcard
 * <?> любой тип (Object),
 * <? extends X> класс Х или его подкласс
 * <? super Y> класс Y или его суперкласс
 * В generic мы можем использовать wildcard
 */
public class WildcardWIthGeneric {
    public static void main(String[] args) {
        //Работает все хорошо
        X x = new Y();
        //Тут тип имеем X от него зависит то какие методы мы можем вызвать, но объект имеем типа Y, будет вызвана реализация метода sayX объекта Y
        x.sayX();

        //Тут в ArrayList неявно ставится X вот так ArrayList<X> так как ArrayList это подтип List с одним и тем же generic
        List<X> list1 = new ArrayList<>();

        //Работать не будет так как ArrayList<Y> это не под тип List<X>
//        List<X> list1 = new ArrayList<Y>();
        /**
         * Если бы можно было писать List<X> list1 = new ArrayList<Y>(); то следующий код
         * падал бы только во время выполнения, а так он падает раньше и это здорово
         *         List<Number> numbers = new ArrayList<Integer>();
         *         numbers.add(11);
         *         numbers.add(5.55);
         */

        //wildcard в generic
        MyNumber<Double> myDouble = new MyNumber<>(22.3);
    }

    static class X {
        public void sayX() {
            System.out.println("X");
        }
    }

    static class Y extends X {
        public void sayX() {
            System.out.println("y");
        }
        public void sayY() {
            System.out.println("Y");
        }
    }

    /***
     * В generic можно использовать wildcard
     */
    static class MyNumber <T extends Number> {
        private T value;

        public MyNumber(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    /***
     * В generic можно использовать wildcard и указывать интерфейсы
     */
    static class MyNumber2 <T extends Number&I1&I2> {
        private T value;

        public MyNumber2(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

}

interface I1 {}
interface I2 {}
