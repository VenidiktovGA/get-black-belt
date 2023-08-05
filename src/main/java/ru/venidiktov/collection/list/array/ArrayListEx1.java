package ru.venidiktov.collection.list.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx1 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("Jordan 23");
        System.out.println(strings);


        List<String> arrayList2 = new ArrayList<>(200);

        List<String> arrayList3 = new ArrayList<>(strings);
        System.out.println(arrayList3);
        System.out.println("Листы указывают на один и тот же объект = " + (strings == arrayList3));

        //Можно создать список с сырым типом ТАК ДЕЛАТЬ НЕ НУЖНО!
        List list = new ArrayList();
        list.add("Hello");
        list.add(11);
        System.out.println(list);
    }
}
