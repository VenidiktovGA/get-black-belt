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
public class Wildcard {
    public static void main(String[] args) {
        //Так писать нельзя потому что ArrayList<Integer> это не подтип List<Number>
//        List<Number> numbers = new ArrayList<Integer>();

        //Так можно! Так как List<?> это супер тип для любого листа!
        List<?> numbers = new ArrayList<Integer>();
//        numbers.add(22); //Это не сработает, будет ошибка компиляции так как на момент компиляции неизвестен тип списка numbers он равен ?

        //Запускаем метод с ? в качестве аргумента
        List<String> list1 = new ArrayList<>();
        list1.add("java");
        list1.add("sql");
        showList(list1);

        List<Double> list2 = new ArrayList<>();
        list2.add(22.3);
        list2.add(5.55);
        showList(list2);

        /***
         * Bounded wildcards (Границы wildcards)
         * ограничение сверху или снизу
         */
        List<? extends Number> list3 = new ArrayList<Integer>(); //Корректно
        List<? super Number> list4 = new ArrayList<Object>(); //Корректно

        List<Double> doubles = new ArrayList<>();
        doubles.add(55.3);
        doubles.add(2.0);
        doubles.add(33.77);
        var sum = sum(doubles);
        System.out.println("#### Сумма элементов списка = " + sum);
    }

    /***
     * Если из аргумента метода убрать символ '?' и поставить туда Object (List<Object> list)
     * то тогда мы сможем передавать в метод только ArrayList<Object>
     */
    public static void showList(List<?> list) {
        //Это не сработает, будет ошибка компиляции так как на момент компиляции неизвестен тип параметра list
        //и к тому же сюда могут передать list Integer и тогда будет ошибка в run time
//        list.add("hello");
        System.out.println("#### Печатаю содержимое листа ####");
        System.out.println(list);
    }

    public static double sum(List<? extends Number> numbers) {
        double sum = 0;
        for(Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }
}
