package ru.venidiktov.collection.map;

import java.util.Comparator;
import java.util.TreeMap;

/***
 * ОСНОВНАЯ ЗАДАЧА TreeMap ЭТО НАХОЖДЕНИЕ ОТРЕЗКОВ (то есть мы идем до какого то узла и берем все элементы начиная от него)
 * TreeMap --> NavigableMap -> SortedMap
 * TreeMap под капотом работает на структуре "красно-черное дерево"
 * В TreeMap для сортировки ключей и поиска по ключу, объекты ключей должны реализовывать Comparable
 * В TreeMap для поиска по значению, объект значений должны реализовывать equals()
 * HashMap работает быстрее чем TreeMap (В общих случаях)
 */
public class TreeMapEx {
    public static void main(String[] args) {
        TreeMap<Double, String> treeMap = new TreeMap<>();
        treeMap.put(5.4, "5.4");
        treeMap.put(3.5, "3.5");
        treeMap.put(2.0, "2.0");
        treeMap.put(4.4, "4.4");
        treeMap.put(1.4, "1.4");
        // Все отсортировалось по возрастанию ключей
        System.out.println("Tree map = " + treeMap);

        //В TreeMap есть все методы интрефейса Map

        // descendingMap() - Развернуть treeMap в обратную сторону
        System.out.println(treeMap.descendingMap());

        // tailMap() - берет под дерево начиная с узла вниз. Такого ключа может и не быть и метод отработает верно!
        System.out.println("Часть мапы после ключа 4.0 = " + treeMap.tailMap(4.0));

        // headMap() - берет поддерево от корня до узла. Такого ключа может и не быть и метод отработает верно!
        System.out.println("Часть мыпы до клча 3.0 = " + treeMap.headMap(3.0));

        // lastEntry()
        System.out.println("Last element treeMap = " + treeMap.lastEntry());

        // firstEntry()
        System.out.println("Firts element treeMap (root tree) = " + treeMap.firstEntry());

        // containsKey - для поиска ключа объект ключа должен реализовывать Comparable
        System.out.println("Key 5.5 contains im treeMap = " + treeMap.containsKey(5.4));

        // containsValue - для поиска значения объект значения должен реализовывать метод equals()
        System.out.println("Value '1.4' contains im treeMap = " + treeMap.containsValue(1.4));

        /***
         * Для того чтобы класс можно было использовать в TreeMap класс должен имплементировать интерфейс Comparable
         */
        TreeMap<Student, Double> treeMap2 = new TreeMap<>();
        var st1 = new Student("Ivanov", "Ivanov", 3);
        var st2 = new Student("Maria", "Ivanova", 4);
        var st3 = new Student("Maria", "Zaeva", 2);
        var st4 = new Student("Yak", "Zaev", 1);
        treeMap2.put(st3, 4.5);
        treeMap2.put(st2, 6.5);
        treeMap2.put(st1, 5.5);
        treeMap2.put(st4, 7.5);
        System.out.println("Tree map of Student implements Comparable = " + treeMap2);

        /***
         * Можно так же при создании TreeMap передать Comparator которым будет сравниваться ключи
         */
        TreeMap<Student, Double> treeMap3 = new TreeMap<>(Comparator.comparingInt(o -> o.course));
        treeMap3.put(st3, 4.5);
        treeMap3.put(st2, 6.5);
        treeMap3.put(st1, 5.5);
        treeMap3.put(st4, 7.5);
        System.out.println("Tree map of Student with external Comparator = " + treeMap3);

    }

    private static class Student implements Comparable<Student> {
        private String name;
        private String surname;
        private int course;

        public Student(String name, String surname, int course) {
            this.name = name;
            this.surname = surname;
            this.course = course;
        }

        @Override
        public int compareTo(Student student) {
            return (name + surname).compareTo(student.name + student.surname);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", course=" + course +
                    '}';
        }
    }
}
