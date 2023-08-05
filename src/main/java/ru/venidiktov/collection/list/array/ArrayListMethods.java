package ru.venidiktov.collection.list.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMethods {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        //add
        strings.add("Hello");
        strings.add(1, "Misha");
        System.out.println(strings);

        //get
        System.out.println("Element by index 1 = " + strings.get(1));

        //set - Заменяет элемент по индексу
        strings.set(1, "Ivan");
        System.out.println("List after set() - " + strings);

        //remove
        strings.remove("Ivan"); //При указании объекта мы ищем объект для удаления по методу equals()
        strings.remove(0);

        //addAll
        List<String> dopList = new ArrayList<>();
        List<String> dopList2 = new ArrayList<>();
        dopList.add("Dop user");
        dopList2.add("Dop user 2");
        strings.addAll(dopList);
        strings.addAll(0, dopList2);
        System.out.println("List after addAll() - " + strings);

        //clear
        strings.clear();
        System.out.println("List after clear() - " + strings);

        //indexOf
        strings.add("Hello");
        strings.add(1,"Hello");
        System.out.println("Index first element 'Hello' = " + strings.indexOf("Hello")); //Для поиска объекта используется метод equals()

        //lastIndexOf
        System.out.println("Index last element 'Hello' = " + strings.lastIndexOf("Hello")); //Для поиска объекта используется метод equals()

        //size
        System.out.println("Size list = " + strings.size());

        //isEmpty
        System.out.println("List is empty = " + strings.isEmpty());

        //contains
        System.out.println("List is contains 'Hello' = " + strings.contains("Hello"));  //Для сравнения объектов используется метод equals()

        //toString
        System.out.println("List ofter roString = " + strings.toString());
    }
}
