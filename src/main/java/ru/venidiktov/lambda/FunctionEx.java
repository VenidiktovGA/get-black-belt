package ru.venidiktov.lambda;

import java.util.List;
import java.util.function.Function;

/**
 * Функциональный интерфейс Function<T,R> из пакета java.util.function - Функция
 * Интерфейс Function используют для подстановки в качестве него lambda выражения
 * Абстрактный метод функционального интерфейса Function выглядит так R apply(T t);
 */
public class FunctionEx {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ivan", 'M', 20, 1, 4.5),
                new Student("Olda", 'F', 22, 2, 6.5),
                new Student("Alex", 'M', 20, 1, 6.7)
        );
        System.out.println("Студенты = " + students);

        /**
         * Средний возраст студентов
         */
        var avgAgeStudents = fun(students, inStudents -> {
            var avgAge = 0;
            for (Student s : inStudents
            ) {
                avgAge += s.age;
            }
            return avgAge / (double) students.size();
        });
        System.out.println("Средний возраст студентов = " + avgAgeStudents);

        /**
         * Средний бал студентов
         */
        var avgGradeStudents = fun(students, inStudents -> {
            var avgGrade = 0;
            for (Student s : inStudents
            ) {
                avgGrade += s.avgGrade;
            }
            return avgGrade / (double) students.size();
        });
        System.out.println("Средний бал студентов = " + avgGradeStudents);
    }

    public static Double fun(List<Student> students, Function<List<Student>, Double> function) {
        return function.apply(students);
    }
}
