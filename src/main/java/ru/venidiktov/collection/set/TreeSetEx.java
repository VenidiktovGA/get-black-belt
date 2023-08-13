package ru.venidiktov.collection.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/***
 * TreeSet основан на TreeMap, просто значения для всех элементов заменены на некое константное значение,
 * как и в TreeMap в TreeSet не может быть Null в качестве ключа, так как
 * В TreeSet как и в TreeMap для сортировки элементов и поиска, объекты должны реализовывать Comparable
 * В TreeMap как и в TreeMap для поиска по значению, объект значений должны реализовывать equals()
 */
public class TreeSetEx {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(8);
        treeSet.add(1);
        treeSet.add(9);
//        treeSet.add(null); Нельзя добавлять null
        System.out.println("tree set = " + treeSet);

        /***
         * Для того чтобы класс можно было использовать в TreeSet класс должен имплементировать интерфейс Comparable
         */
        TreeSet<Student> treeSet2 = new TreeSet<>();
        var st1 = new Student("Ivan", "Ivanov", 3);
        var st2 = new Student("Maria", "Ivanova", 4);
        var st2_2 = new Student("Maria", "Ivanova", 5); // Не добавится, так как это дубликат
        var st3 = new Student("Maria", "Zaeva", 2);
        var st4 = new Student("Yak", "Zaev", 1);
        treeSet2.add(st3);
        treeSet2.add(st2);
        treeSet2.add(st2_2);
        treeSet2.add(st1);
        treeSet2.add(st4);
        System.out.println("TreeSet of Student implements Comparable = " + treeSet2);

        /***
         * Можно так же при создании TreeSet передать Comparator которым будет сравниваться ключи
         */
        TreeSet<Student> treeSet3 = new TreeSet<>(Comparator.comparingInt(o -> o.course));
        treeSet3.add(st3);
        treeSet3.add(st2);
        treeSet3.add(st2_2);
        treeSet3.add(st1);
        treeSet3.add(st4);
        System.out.println("TreeSet of Student with external Comparator = " + treeSet3);

        // first()
        System.out.println("First student by course = " + treeSet3.first());

        // last()
        System.out.println("Last student by course = " + treeSet3.last());

        // headSet()
        var oleg = new Student("Oleg", "Olegov", 3);
        System.out.println("Students by course from had to < Oleg course = " + treeSet3.headSet(oleg));

        //tailSet()
        System.out.println("Students by course form >= Oleg course to tail = " + treeSet3.tailSet(oleg));

        //subSet
        var lena = new Student("Elena", "ELenova", 1);
        System.out.println("Students from <= Elena course to > Oleg course = " + treeSet3.subSet(lena, oleg));
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
