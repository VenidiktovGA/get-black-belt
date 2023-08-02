package ru.venidiktov.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListMethods2 {
    public static void main(String[] args) {
        /***
         * Arrays.asList()
         * Массив и лист полученный с помощью Arrays.asList() тесно связаны, что видно ниже
         */
        StringBuilder sb1 = new StringBuilder("A");
        StringBuilder sb2 = new StringBuilder("B");
        StringBuilder sb3 = new StringBuilder("C");
        StringBuilder sb4 = new StringBuilder("D");
        StringBuilder[] array = {sb1, sb2, sb3, sb4};
        List<StringBuilder> list = Arrays.asList(array);
        System.out.println("Lis form Arrays.asList(array) - " + list);

        array[0].append("!!!");
        System.out.println("Lis form Arrays.asList(array) before append for 0 element - " + list);

        array[0] = new StringBuilder("Replace element");
        System.out.println("Lis form Arrays.asList(array) before replace for 0 element - " + list);

        //removeAll
        List<String> list1 = new ArrayList<>();
        list1.add("Ok");
        list1.add("fail");
        List<String> list2 = new ArrayList<>();
        list2.add("fail");
        list1.removeAll(list2);
        System.out.println("list1 after list1.removeAll(list2) = " + list1);

        //retainAll
        List<String> list3 = new ArrayList<>();
        list3.add("Ok");
        list3.add("Fail");
        list3.add("mel");
        List<String> list4 = new ArrayList<>();
        list4.add("Ok");
        list4.add("Fail");
        list3.retainAll(list4);
        System.out.println("list3 after list3.retainAll(list4) - " + list3);

        //containsAll
        List<String> list5 = new ArrayList<>();
        list3.add("Ok");
        list3.add("Fail");
        list3.add("mel");
        List<String> list6 = new ArrayList<>();
        list4.add("Ok");
        list4.add("Fail");
        list3.retainAll(list4);
        System.out.println("list5 contains all elements from list6 - " + list5.containsAll(list6));

        /***
         * subList
         * Лист и полученный из него под лист связанны это видно ниже
         */
        List<String> list7 = new ArrayList<>();
        list7.add("Ok");
        list7.add("Fail");
        list7.add("keil");
        list7.add("bax");
        List<String> subList = list7.subList(1, (list7.size() - 1));
        System.out.println("Sub list from 1 index to end index with list7 - " + subList);
        subList.add("Hello");
        System.out.println("Min list after add element to sub list - " + list7);
        list7.add("Kola");
//        System.out.println("Sub list from 1 index to end index with list7 - " + subList); Это вывести не получится будет ConcurrentModificationException

        //toArray
        List<String> list8 = new ArrayList<>();
        list8.add("Ok");
        list8.add("Fail");
        String[] array2 = list8.toArray(new String[0]); //Можно написать 0 и не думать о размере массива
        System.out.print("Array after list8.toArray {");
        for(int i = 0; i < array2.length; i++) {
        System.out.print(array2[i] + " ");
        }
        System.out.print("}");
        System.out.println();

        //List.of Лис полученный методом of НЕИЗМЕНЯЕМ + лист не может содержать null
        List<Integer> list9 = List.of(3, 8, 23);
        System.out.println("List build for List.of = " + list9);
//        list9.add(22); list9 неизменяемый мы получим UnsupportedOperationException

        //List.copyOf Лис полученный методом copyOf НЕИЗМЕНЯЕМ + лист не может содержать null
        List<Integer> list10 = new ArrayList<>();
        list10.add(22);
        list10.add(44);
        List<Integer> list11 = List.copyOf(list9);
        System.out.printf("List build for List.copyOf = " + list11);
//        list11.add(55); list11 неизменяемый мы получим UnsupportedOperationException
    }
}
