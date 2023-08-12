package ru.venidiktov.collection.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetEx2 {
    public static void main(String[] args) {

        /***
         * ОБЪЕДИНЕНИЕ
         */
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(5);
        set1.add(3);
        set1.add(2);
        System.out.println("set1 = " + set1);

        Set<Integer> set2 = new HashSet<>();
        set2.add(6);
        set2.add(9);
        set2.add(7);
        set2.add(8);
        System.out.println("set2 = " + set2);

        Set<Integer> set1Set2Union = set1;
        set1Set2Union.addAll(set2);
        System.out.println("ОБЪЕДИНЕНИЕ МНОЖЕСТВ set1 и set2 = " + set1Set2Union);
        /***
         *set мутабельный, метод addAll изменят set для которого вызывается
         * set1 изменился не смотря на то что мы присвоили ссылку на него в другую переменную это не имеет значения он изменился
         */
        System.out.println("set1 мутабельный он изменился = " + set1);
        System.out.println("set2 мутабельный он не изменился = " + set2);


        /***
         * ПЕРЕСЕЧЕНИЕ
         */
        Set<Integer> set3 = new HashSet<>();
        set3.add(1);
        set3.add(7);
        set3.add(5);
        set3.add(3);
        set3.add(9);
        set3.add(2);
        System.out.println("set1 = " + set3);

        Set<Integer> set4 = new HashSet<>();
        set4.add(6);
        set4.add(9);
        set4.add(1);
        set4.add(7);
        set4.add(3);
        set4.add(8);
        System.out.println("set2 = " + set4);
        Set<Integer> set3Set4Intersect = set3;
        set3Set4Intersect.retainAll(set4);
        System.out.println("ПЕРЕСЕЧЕНИЕ МНОЖЕСТВ set3 и set4 = " + set3Set4Intersect);
        /***
         *set мутабельный, метод retainAll изменят set для которого вызывается
         * set3 изменился не смотря на то что мы присвоили ссылку на него в другую переменную это не имеет значения он изменился
         */
        System.out.println("set3 мутабельный он изменился = " + set3);
        System.out.println("set4 мутабельный он не изменился = " + set4);


        /***
         * РАЗНОСТЬ
         */
        Set<Integer> set5 = new HashSet<>();
        set5.add(1);
        set5.add(7);
        set5.add(5);
        set5.add(3);
        set5.add(9);
        set5.add(2);
        System.out.println("set1 = " + set5);

        Set<Integer> set6 = new HashSet<>();
        set6.add(6);
        set6.add(9);
        set6.add(1);
        set6.add(7);
        set6.add(3);
        set6.add(8);
        System.out.println("set2 = " + set6);
        Set<Integer> set5Set6Subtract = set5;
        set5Set6Subtract.removeAll(set6);
        System.out.println("РАЗНОСТЬ МНОЖЕСТВ set3 и set4 = " + set5Set6Subtract);
        /***
         *set мутабельный, метод removeAll изменят set для которого вызывается
         * set3 изменился не смотря на то что мы присвоили ссылку на него в другую переменную это не имеет значения он изменился
         */
        System.out.println("set3 мутабельный он изменился = " + set5);
        System.out.println("set4 мутабельный он не изменился = " + set6);

    }
}
