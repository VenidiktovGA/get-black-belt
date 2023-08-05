package ru.venidiktov.collection;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.venidiktov.comparation.Test4;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-3);
        list.add(8);
        list.add(12);
        list.add(-8);
        list.add(0);
        list.add(5);
        list.add(10);
        list.add(1);
        list.add(150);
        list.add(-30);
        list.add(19);
        //Если коллекция не отсортирована то бинарный поиск будет работать не верно!
        int index1 = Collections.binarySearch(list, 12); //Отрицательное число значит что элемент не найден
        System.out.println("В неотсортированной коллекции индекс элемента '12' = " + index1);

        //Если коллекция отсортирована то бинарный поиск будет работать!
        Collections.sort(list);
        int index2 = Collections.binarySearch(list, 12);
        System.out.println("В отсортированной коллекции индекс элемента '12' = " + index2);

        //Не забываем что для сортировки объектов и последующего поиска через binarySearch,
        // объект должен имплементировать интерфейс Comparable или
        // в метод сортировки нужно передать Comparator (equals тут не нужен!!!)
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(124, "Ivan", "Ivanov", 100));
        employees.add(new Employee(12, "Maria", "Mom", 90));
        employees.add(new Employee(44, "Iosif", "Carp", 101));
        employees.add(new Employee(34, "Lada", "Sedan", 80));
        employees.add(new Employee(55, "Ola", "Olegovna", 70));
        Collections.sort(employees);
        var employeeIndex = Collections.binarySearch(employees, new Employee(44, "", "", 0));
        System.out.println("В отсортированном списке работников работник с id = 44, находится оп индексу " + employeeIndex);

        //reverse
        System.out.println("list employee before reverse - " + employees);
        Collections.reverse(employees);
        System.out.println("list employee after reverse - " + employees);

        //shuffle
        Collections.shuffle(employees);
        System.out.println("list employee after shuffle - " + employees);
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
}
