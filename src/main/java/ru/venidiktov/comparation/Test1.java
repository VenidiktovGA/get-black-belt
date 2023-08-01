package ru.venidiktov.comparation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Сравнение по умолчанию
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("Ivan");
        list1.add("Maria");
        list1.add("Iosif");
        System.out.println("list1 до сортировки = " + list1); //list1 без сортировки = [Ivan, Maria, Iosif]
        //Сортировка в лексикографическом порядке (Как в словаре, слова на 'a' самые маленькие на 'z' самые большие)
        Collections.sort(list1);
        System.out.println("list1 после сортировки = " + list1); //list1 с сортировкой = [Iosif, Ivan, Maria]
    }
}
