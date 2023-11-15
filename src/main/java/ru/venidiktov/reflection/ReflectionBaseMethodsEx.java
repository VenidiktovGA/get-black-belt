package ru.venidiktov.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Рефлексия — это механизм исследования данных о программе во время её выполнения.
 * Рефлексия позволяет исследовать информацию о полях, методах и конструкторах классов во время выполнения программы.
 */
public class ReflectionBaseMethodsEx {
    public static void main(String[] args) {
        /**
         * 3 варианта создание класса Class нужного нам класса
         */
        Class employeeClass = null;
        try {
            employeeClass = Class.forName("ru.venidiktov.reflection.ReflectionBaseMethodsEx$Employee");
        } catch (ClassNotFoundException e) {
            System.out.println(String.format("По пути %s не найден класс", "ru.venidiktov.reflection.ReflectionEx$Employee"));
        }
        Class employeeClass2 = Employee.class;
        Class employeeClass3 = new Employee().getClass();
        System.out.println("3 варианта создание класса Class нужного нам класса:");
        System.out.println("1 через указание пакета где располагается класс");
        System.out.println("2 можно взять поле class у нашего класса");
        System.out.println("3 можно вызвать метод getClass() у нашего класса");
        System.out.println();

        /**
         * можем получить какое либо поле класса из его объекта класса Class
         */
        System.out.println("можем получить какое либо поле класса из его объекта класса Class");
        try {
            Field idField = employeeClass.getField("id");
            System.out.println("Тип поля id = " + idField.getType());
        } catch (NoSuchFieldException e) {
            System.out.println("Поле id не найдено в класса Employee");
        }
        System.out.println();

        /**
         * можем получить все поля класса через его объект класса Class методом getFields()
         * полей с модификатором private мы тут не получим
         */
        System.out.println("можем получить все поля класса через его объект класса Class методом getFields()");
        Field[] fields = employeeClass.getFields();
        for (Field field : fields) {
            System.out.println(String.format("Поле %s имеет тип %s", field.getName(), field.getType()));
        }
        System.out.println();

        /**
         * получить поле с модификатором private
         */
        System.out.println();
        Field[] declaredFields = employeeClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(String.format("Поле %s типа %s с модификатором %s", declaredField.getName(), declaredField.getType(), declaredField.getModifiers()));
        }
        System.out.println();

        /**
         * Получить метод класса
         * Методом getMethod() мы не сможем получить приватные методы
         */
        try {
            System.out.println("Получить метод класса, кроме приватных");
            Method increaseSalary = employeeClass.getMethod("increaseSalary"); // Метод getMethod() может принимать типы параметров для поиска метода среди перегруженных
            System.out.println(String.format("Метод %s возвращаемое значение %s типы параметров %s", increaseSalary.getName(),
                    increaseSalary.getReturnType(), Arrays.toString(increaseSalary.getParameterTypes())));
        } catch (NoSuchMethodException e) {
            System.out.println("Метода не найден");
            e.printStackTrace();
        }
        System.out.println();

        /**
         * Получить все методы класса
         * Метод getMethods() возвращает и унаследованные методы то же
         * Методом getMethods() мы не сможем получить приватные методы
         */
        System.out.println("Получить все методы класса, кроме приватных");
        Method[] employeeClassMethods = employeeClass.getMethods();
        for (Method employeeClassMethod : employeeClassMethods) {
            System.out.println(String.format("Метод %s возвращаемое значение %s типы параметров %s", employeeClassMethod.getName(),
                    employeeClassMethod.getReturnType(), Arrays.toString(employeeClassMethod.getParameterTypes())));
        }
        System.out.println();

        /**
         * Получить все методы класса включая приватные методы но не включая унаследованные методы
         */
        System.out.println("Получить все методы класса включая приватные методы но не включая унаследованные методы");
        Method[] employeeClassMethods2 = employeeClass.getDeclaredMethods();
        for (Method employeeClassMethod : employeeClassMethods2) {
            System.out.println(String.format("Метод %s возвращаемое значение %s типы параметров %s", employeeClassMethod.getName(),
                    employeeClassMethod.getReturnType(), Arrays.toString(employeeClassMethod.getParameterTypes())));
            if (Modifier.isPrivate(employeeClassMethod.getModifiers())) {
                System.out.println(String.format("Метод %s приватный", employeeClassMethod.getName()));
            }
        }
        System.out.println();

        /**
         * Получение конструктора класса
         */
        System.out.println("Получение конструктора класса");
        try {
            Constructor employeeClassConstructor = employeeClass.getConstructor(); // Вернет конструктор без параметров
            System.out.println(String.format("Конструктор %s имеет %s параметров типов %s", employeeClassConstructor.getName(),
                    employeeClassConstructor.getParameterCount(), Arrays.toString(employeeClassConstructor.getParameterTypes())));
            Constructor employeeClassConstructor2 = employeeClass.getConstructor(int.class, String.class); // Вернет где два параметра указанных типов
            System.out.println(String.format("Конструктор %s имеет %s параметров типов %s", employeeClassConstructor2.getName(),
                    employeeClassConstructor2.getParameterCount(), Arrays.toString(employeeClassConstructor2.getParameterTypes())));
        } catch (NoSuchMethodException e) {
            System.out.println("Конструктор не найден");
            e.printStackTrace();
        }
        System.out.println();

        /**
         * Получить все конструкторы кроме приватных
         */
        System.out.println("Получить все конструкторы кроме приватных");
        try {
            Constructor[] employeeClassConstructors = employeeClass.getConstructors();
            for (Constructor employeeClassConstructor : employeeClassConstructors) {
                System.out.println(String.format("Конструктор %s имеет %s параметров типов %s", employeeClassConstructor.getName(),
                        employeeClassConstructor.getParameterCount(), Arrays.toString(employeeClassConstructor.getParameterTypes())));

            }
        } catch (SecurityException e) {
            System.out.println("Не удалось получить конструкторы");
            e.printStackTrace();
        }
        System.out.println();

        /**
         * Получить все конструкторы включая приватные
         */
        System.out.println("Получить все конструкторы включая приватные");
        try {
            Constructor[] employeeClassConstructors = employeeClass.getDeclaredConstructors();
            for (Constructor employeeClassConstructor : employeeClassConstructors) {
                System.out.println(String.format("Конструктор %s имеет %s параметров типов %s", employeeClassConstructor.getName(),
                        employeeClassConstructor.getParameterCount(), Arrays.toString(employeeClassConstructor.getParameterTypes())));

            }
        } catch (SecurityException e) {
            System.out.println("Не удалось получить конструкторы");
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
