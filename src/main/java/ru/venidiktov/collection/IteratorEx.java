package ru.venidiktov.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * Iterator позволяет итерироваться по коллекции
 * iterator.hasNext() - говорит есть ли следующий элемент
 * iterator.next() - достает следующий элемент, он будет в итераторе
 * iterator.remove() - удаляет элемент из итератора
 */
public class IteratorEx {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Orange");
        list.add("Blue");

        System.out.println("#### Выводим элементы списка через iterator");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
//            iterator.remove(); Удалить элемент
        }
    }
}
