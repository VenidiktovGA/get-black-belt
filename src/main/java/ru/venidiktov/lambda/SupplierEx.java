package ru.venidiktov.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Функциональный интерфейс Supplier<T> из пакета java.util.function - Поставщик
 * Интерфейс Supplier используют для подстановки в качестве него lambda выражения
 * Абстрактный метод функционального интерфейса Predicate выглядит так boolean T get();
 */
public class SupplierEx {
    public static void main(String[] args) {

        System.out.println("Создаем студентов через интерфейс Supplier");
        var students1 = createStudents(() -> new Student("Ivan", 'M', 20, 3, 5.0));
        System.out.println("Студенты = " + students1);

        System.out.println("Создаем студентов через интерфейс Supplier");
        var students2 = createStudents(() -> new Student("Maria", 'F', 19, 2, 7.7));
        System.out.println("Студенты = " + students2);
    }

    public static List<Student> createStudents(Supplier<Student> supplier) {
        var students = new ArrayList<Student>();
        for (int i = 0; i < 3; i++) {
            students.add(supplier.get());
        }
        return students;
    }
}
