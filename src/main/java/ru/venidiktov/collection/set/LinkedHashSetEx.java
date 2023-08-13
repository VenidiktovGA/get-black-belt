package ru.venidiktov.collection.set;

import java.util.LinkedHashSet;

/***
 * Так как в основе LinkedHasSet лежит HashMap то возможности сортировать элементы по их использованию нет,
 * сортировка идет только по добавлению элементов
 */
public class LinkedHashSetEx {
    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(22);
        linkedHashSet.add(11);
        linkedHashSet.add(3);
        linkedHashSet.add(9);
        linkedHashSet.add(15);
        System.out.println("LinkedHasSet = " + linkedHashSet);

        // Элементы не сортируются в порядке их использования
        System.out.println("linked hash set contains 3 = " + linkedHashSet.contains(3));
        System.out.println("LinkedHasSet = " + linkedHashSet);
    }
}
