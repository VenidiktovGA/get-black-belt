package ru.venidiktov.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Без Generics в java коллекции были сырого типа (Object)
 * Проблема сырого типа в том сто в коллекцию можно положить все что угодно, а после при доставании из нее объекта каст не пройдет
 * Generics были придуманы для типобезопасности, совместимость объекта с типом коллекции идет во время компиляции,
 * так же generics позволяют переиспользовать код
 * */
public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList<>();
//        List<String> list = new ArrayList(); Если не поставить <> после ArrayList это то же будет значить сырой тип
        list.add("hello");
        list.add("by");
//        list.add(1); //Если добавим это то будте ClassCastException
        for (Object o: list) {
            System.out.println(o + "length = " + ((String)o).length());
        }
    }
}
