package ru.venidiktov.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Функциональный интерфейс Predicate<T> из пакета java.util.function - Условие
 * Интерфейс Predicate используют для подстановки в качестве него lambda выражения
 * Абстрактный метод функционального интерфейса Predicate выглядит так boolean test(T t);
 */
public class PredicateEx {
    public static void main(String[] args) {
        var st1 = new Student("Ivan", 'M', 20, 1, 4.5);
        var st2 = new Student("Olda", 'F', 22, 2, 6.5);
        var st3 = new Student("Anna", 'F', 21, 3, 8.7);
        var st4 = new Student("Iosif", 'M', 23, 3, 7.7);
        var st5 = new Student("Alex", 'M', 20, 1, 6.7);
        List<Student> students = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

        System.out.println("------------------------------------");
        System.out.println("Студенты = " + students);

        System.out.println("------------------------------------");
        System.out.println("Фильтруем студентов у кого avgGrade > 8.0");
        testPredicate(students, s -> s.avgGrade > 8.0);

        /**
         * Predicate lambda выражения можно объединять!!!
         */
        System.out.println("------------------------------------");
        System.out.println("Predicate lambda выражения можно объединять через AND!");
        Predicate<Student> predicateSex = s -> s.sex == 'M';
        Predicate<Student> predicateAge = s -> s.age > 22;
        testPredicate(students, predicateSex.and(predicateAge));
        System.out.println("Predicate lambda выражения можно объединять через ORE!");
        testPredicate(students, predicateSex.or(predicateAge));

        /**
         * Predicate можно отрицать
         */
        System.out.println("------------------------------------");
        System.out.println("Predicate lambda выражения можно отрицать через negate!");
        testPredicate(students, predicateSex.negate());

        System.out.println("------------------------------------");
        System.out.println("Метод removeIf принимает Predicate");
        students.removeIf(s -> s.sex == 'M');
        System.out.println("В списке остались только девушки = " + students);

        System.out.println("------------------------------------");


    }

    /**
     * Метод принимающий Predicate
     */
    public static void testPredicate(List<Student> students, Predicate<Student> predicate) {
        for (Student student : students) {
            if (predicate.test(student)) {
                System.out.println(String.format("Student with name %s meets the condition", student.name));
            }
        }
    }
}
