package ru.venidiktov.comparation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Использование Comparable для сравнения
 */
public class Test2 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(22, "Ivan", "Ivanov", 100));
        employees.add(new Employee(15, "Maria", "Marieva", 75));
        employees.add(new Employee(15, "Maria", "Marieva", 60));
        employees.add(new Employee(123, "Iosif", "Iosifov", 75));
        System.out.println("employees до сортировки = " + employees); //list1 до сортировки = [Employee{'Ivan', 'Maria', 'Iosif'}]
        Collections.sort(employees);
        System.out.println("employees после сортировки = " + employees); //list1 после сортировки = [Employee{'Maria', 'Ivan', 'Iosif'}]
//        Arrays.sort(new Employee[] {emp1, emp2, emp3}); Если класс не имплементит Comparable будет ошибка в RunTime!
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

        // 1 текущий объект больше o
        // 0 текущий объект равен o
        // -1 текущий объект меньше o
        @Override
        public int compareTo(Employee o) {
//        return this.id.compareTo(o.id); //Используем для сравнения id, вызываем у Integer его метод compareTo
//        return this.id - o.id
            var res = this.id.compareTo(o.id);
            if(res == 0) {
                res = this.salary.compareTo(o.salary); //Если id равны то сравниваем salary
            }
            return res;
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
}
