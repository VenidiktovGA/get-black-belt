package ru.venidiktov.collection.list.linked;

import java.util.LinkedList;
import java.util.List;

public class LinkedListEx1 {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Petr");
        list.add("Maria");
        list.add("Ivan");
        System.out.println("LinkedList list = " + list);
        System.out.println("Element by index 2 = " + list.get(2));
        list.add(1, "Iosif");
        System.out.println("LinkedList list = " + list);
    }
}
