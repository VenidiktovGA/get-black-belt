package ru.venidiktov.lambda;

import java.util.List;
import java.util.function.Consumer;

/**
 * Функциональный интерфейс Consumer из пакета java.util.function - Потребитель
 * Интерфейс Consumer используют для подстановки в качестве него lambda выражения
 * Абстрактный метод функционального интерфейса Predicate выглядит так boolean void accept(T t);
 */
public class ConsumerEx {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ivan", 'M', 20, 1, 4.5),
                new Student("Olda", 'F', 22, 2, 6.5),
                new Student("Alex", 'M', 20, 1, 6.7)
        );
        System.out.println("Студенты до перевода = " + students);

        System.out.println("Переводим студентов на следующий курс");
        changeStudents(students, s -> {
            var previousCourse = s.course;
            s.course += 1;
            System.out.println(String.format("Студент %s переведен с %s на %s курс", s.name, previousCourse, s.course));
        });
        System.out.println("Студенты после перевода = " + students);

        /**
         * Метод forEach у коллекций так же принимает в качестве аргумента интерфейс типа Consumer
         */
        System.out.println("Пол всех студентов");
        students.forEach(s -> System.out.println(s.name + " его пол = " + s.sex));

    }

    public static void changeStudents(List<Student> students, Consumer<Student> consumer) {
        for (Student student : students) {
            consumer.accept(student);
        }
    }
}
