package ru.venidiktov.collection.map;

import java.util.LinkedHashMap;

/***
 * LinkedHashMap похожа на HashMap, но в дополнение элементы LinkedHashMap имеют ссылки на следующий и предыдущий элемент
 * в порядке добавления не в порядке их положения в бакетах
 */
public class LinkedHashMapEx {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(100, "Maria");
        linkedHashMap.put(15, "Ivan");
        linkedHashMap.put(33, "Petrov");
        linkedHashMap.put(null, "Ivan");
        linkedHashMap.put(1, null);

        //Элементы LinkedHashMap выводит элементы в порядке добавления
        System.out.println("linkedHashMap = " + linkedHashMap);

        /***
         * При создании в LinkedHashMap как и в HashMap можно указать capacity, loadFactor
         * + можно указать в каком порядке делать ссылки между элементами в порядке их добавления или использования
         */
        LinkedHashMap<Integer, String> linkedHashMap2 = new LinkedHashMap<>(8, 0.4f, true);
        linkedHashMap2.put(100, "Maria");
        linkedHashMap2.put(15, "Ivan");
        linkedHashMap2.put(33, "Petrov");
        System.out.println("LinkedHashMap с порядком ссылок в качестве добавления = " + linkedHashMap2);

        LinkedHashMap<Integer, String> linkedHashMap3 = new LinkedHashMap<>(8, 0.4f, true);
        linkedHashMap3.put(100, "Maria");
        linkedHashMap3.put(15, "Ivan");
        linkedHashMap3.put(33, "Petrov");
        linkedHashMap3.get(100); // Данный элемент с первого станет последним
        System.out.println("LinkedHashMap с порядком ссылок в качестве использования = " + linkedHashMap3);
    }
}
