package ru.venidiktov.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Рефлексия — это механизм исследования данных о программе во время её выполнения.
 * Рефлексия позволяет исследовать информацию о полях, методах и конструкторах классов во время выполнения программы.
 */
public class ReflectionProfessionalEx {
    public static void main(String[] args) {
        Class employeeClass = null;
        try {
            employeeClass = Class.forName("ru.venidiktov.reflection.ReflectionProfessionalEx$Employee");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс Employee не найден");
            e.printStackTrace();
        }

        /**
         * Создать экземпляр класса
         * Метод newInstance() Depricated!!!
         */
        try {
            System.out.println("Создание экземпляра класса через newInstance у самого класса, устарел!");
            Employee employeeInstance = (Employee) employeeClass.newInstance();
            System.out.println("Создали объект = " + employeeInstance);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        /**
         * Рекомендуется экземпляр класса создавать через его конструктор
         */
        Employee alex = null;
        try {
            System.out.println("Создание экземпляра класса через newInstance у конструктора класса, рекомендуется!");
            Constructor<Employee> constructor = employeeClass.getConstructor();
//            Constructor constructor = employeeClass.getConstructor();
//            Employee employeeInstance2 = (Employee) constructor.newInstance();
            Employee employeeInstance2 = constructor.newInstance();
            System.out.println("Создали объект = " + employeeInstance2);

            Constructor<Employee> constructor2 = employeeClass.getConstructor(int.class, String.class);
            alex = constructor2.newInstance(22, "Alex");
            System.out.println("Создали объект = " + alex);

        } catch (NoSuchMethodException e) {
            System.out.println("Конструктор не найден");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println();

        /**
         * Вызов метода объекта
         */
        System.out.println("Вызываем метод setSalary() объекта");
        try {
            Method setSalary = employeeClass.getMethod("setSalary", double.class);
            System.out.println("Было = " + alex);
            setSalary.invoke(alex, 5000);
            System.out.println("Стало = " + alex);
        } catch (NoSuchMethodException e) {
            System.out.println("Метод не найден");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        /**
         * Доступ к полю объекта
         */
        System.out.println("Доступ к полю объекта");
        try {
            Field name = employeeClass.getField("name");
            System.out.println(String.format("В объекте %s. Поле name = %s", alex, (String) name.get(alex)));
        } catch (NoSuchFieldException e) {
            System.out.println("Поле не найдено");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        /**
         * Доступ к приватному полю если даже оно скрыто (нем методов для доступа)
         */
        System.out.println("Доступ к приватному полю если даже оно скрыто (нем методов для доступа)");
        try {
            Field salary = employeeClass.getDeclaredField("salary");
//            Field salary = employeeClass.getField("salary");
//            salary.setAccessible(true);
            System.out.println(String.format("В объекте %s. Поле salary = %s", alex, (Double) salary.get(alex)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Поле не найдено");
            e.printStackTrace();
        }
        System.out.println();

        /**
         * Изменить поле без set метода
         */
        System.out.println("Изменить поле без set метода");
        try {
            Field id = employeeClass.getField("id");
            System.out.println("Было = " + alex);
            id.set(alex, 33);
            System.out.println("Было = " + alex);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Поле не найдено");
            e.printStackTrace();
        }
        System.out.println();

    }

    private static class Employee {
        public int id;
        public String name;
        private double salary;

        public Employee() {
        }

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        private void changeName(String newName) {
            name = newName;
            System.out.println("Новое имя = " + newName);
        }

        public void increaseSalary() {
            salary *= 2;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
