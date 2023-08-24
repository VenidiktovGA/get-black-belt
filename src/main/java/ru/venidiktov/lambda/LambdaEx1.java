package ru.venidiktov.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Плавно подходим к Lambda выражениям
 */
public class LambdaEx1 {
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

        LambdaEx1 info = new LambdaEx1();
        /**
         * 1 Методы прописанные в классе
         * Самописные методы которые делают примерно одно и тоже, проверяют некое условие и печатают информацию
         * Плохо: дублирование кода + строго прописаны условия в коде
         */
        info.printOverGrade(students, 6);
        System.out.println("-----------------------");
        info.printUnderAge(students, 22);
        System.out.println("-----------------------");
        info.printMixCondition(students, 20, 7, 'F');
        System.out.println("-----------------------");

        /**
         * 2 Анонимный класс
         * Метод который принимает объект интерфейса у которого есть метод условия по которому можно определить подходит ли объект под это условие
         * Если не создавать анонимный класс а просто имплементирвоть интерефейс обычным классом то это плохо так как в классе имплементирующем StudentChecks строго указано условие
         * Ананимный класс плох тем что нужно писать много кода который напрямую не связан с действием
         */
        info.checkStudent(students, new MyChecks() { //Это анонимный класс но можно конечно и простой класс создать и передать
            @Override
            public boolean check(Student student) {
                return student.age > 20;
            }
        });
        System.out.println("-----------------------");

        /**
         * 3 Lambda выражение
         * Lambda выражение может быть подставлено везде где ожидается функциональный интерфейс,
         * под капотом Lambda выражение преобразуется в анонимный класс реализующий функциональный интерфейс
         * Удобно, так как не нужно писать много инфраструктурного кода как в анонимном классе
         * Lambda выражения можно грубо представить как анонимный метод
         */
        info.checkStudent(students, s -> s.age > 23);
        System.out.println("-----------------------");

        /**
         * Lambda выражение можно вынести в переменную функционального интерфейса!
         */
        MyChecks myChecks = s -> s.age > 22;
        info.checkStudent(students, myChecks);

    }

    void checkStudent(List<Student> students, MyChecks myChecks) {
        for (Student s : students) {
            if (myChecks.check(s)) {
                System.out.printf("Student with name '%s' meets the condition%n", s.name);
            }
        }
    }

    void printOverGrade(List<Student> list, double grade) {
        for (Student s : list) {
            if (s.avgGrade > grade) {
                System.out.printf("Student with name '%s' has grade more them '%s'%n", s.name, grade);
            }
        }
    }

    void printUnderAge(List<Student> list, int age) {
        for (Student s : list) {
            if (s.age < age) {
                System.out.printf("Student with name '%s' has age less them '%s'%n", s.name, age);
            }
        }
    }

    void printMixCondition(List<Student> list, int age, double grade, char sex) {
        for (Student s : list) {
            if (s.age > age && s.avgGrade > grade && s.sex == sex) {
                System.out.printf("Student with name '%s' has age more them '%s' and grade more them '%s' and sex '%s'%n", s.name, age, grade, sex);
            }
        }
    }
}
