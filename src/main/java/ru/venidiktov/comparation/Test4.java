package ru.venidiktov.comparation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Если класс реализует интерфейс comparable и в метод для сортировки передается Comparator
 * то Comparator имеет приоритет, по нему будут сравниваться объекты
 * */
public class Test4 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(22, "Ivan", "Ivanov", 100));
        employees.add(new Employee(15, "Maria", "Marieva", 75));
        employees.add(new Employee(15, "Maria", "Marieva", 60));
        employees.add(new Employee(123, "Iosif", "Iosifov", 75));
        System.out.println("#### employees до сортировки");
        System.out.println(employees);
        Collections.sort(employees);
        System.out.println("#### employees после сортировки по id с помощью Comparable");
        System.out.println(employees);
        employees.sort(new IdComparator());
        System.out.println("#### employees после сортировки по id с помощью Comparator");
        System.out.println(employees);
    }

    static class Employee implements Comparable<Employee> {
        Integer id;
        String name;
        String surname;
        Integer salary;

        public Employee(int id, String name, String surname, int salary) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        @Override
        public int compareTo(Employee o) {
            return o.id - id;
        }
    }

    static class IdComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.id.compareTo(o2.id);
        }
    }
}
