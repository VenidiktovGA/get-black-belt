package ru.venidiktov.comparation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Использование Comparator для сравнения
 * Comparator используется в основном для сравнения в не естественном порядке
 * */
public class Test3 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(22, "Ivan", "Ivanov", 100));
        employees.add(new Employee(15, "Maria", "Marieva", 75));
        employees.add(new Employee(15, "Maria", "Marieva", 60));
        employees.add(new Employee(123, "Iosif", "Iosifov", 75));
        System.out.println("employees до сортировки = " + employees);
        employees.sort(new IdComparator());
        System.out.println("employees после сортировки по id = " + employees);
        employees.sort(new FIOComparator());
        System.out.println("employees после сортировки по ФИО = " + employees);
    }

    static class Employee {
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
    }

    static class IdComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.id.compareTo(o2.id);
        }
    }

    static class FIOComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            var res = o1.name.compareTo(o2.name);
            if(res == 0) res = o1.surname.compareTo(o2.surname);
            return res;
        }
    }
}
