package ru.venidiktov.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/***
 * hasCode - функция представляющая любой объект в виде числа ограниченного размера
 * Методы equal и hasCode очень важны, переопределяя один переопредели другой!
 * equal и hasCode очень важны для правильной работы многих структур и методов
 */
public class HashCodeEx1 {
    public static void main(String[] args) {
        /**
         * Если не переопределить hasCode() и переопределить equals()
         */
        Map<Student, Double> map = new HashMap<>();
        var st1 = new Student("Maria", "Magdalena", 3);
        var st2 = new Student("Alexandr", "Makidonskiy", 3);
        var st3 = new Student("Gennady", "Venidiktov", 1);
        map.put(st1, 7.5);
        map.put(st2, 8.0);
        map.put(st3, 7.7);
        System.out.println(map);

        var st4 = new Student("Gennady", "Venidiktov", 1);
        //Так как в Student не переопределен hasCode мы не найдем объект,
        //так как HasMap сначала ищет совпадение по hasCode() методу и далее проверяет объект по equal() методу
        System.out.println("Gennady is containing = " + map.containsKey(st4));

        /**
         * Если переопределить hasCode() и equals()
         */
        Map<Student2, Double> map2 = new HashMap<>();
        var st21 = new Student2("Maria", "Magdalena", 3);
        var st22 = new Student2("Alexandr", "Makidonskiy", 3);
        var st23 = new Student2("Gennady", "Venidiktov", 1);
        map2.put(st21, 7.5);
        map2.put(st22, 8.0);
        map2.put(st23, 7.7);
        System.out.println(map2);

        var st24 = new Student2("Gennady", "Venidiktov", 1);
        //Так как в Student2 переопределен hasCode мы найдем объект,
        //так как HasMap сначала ищет совпадение по hasCode() методу и далее проверяет объект по equal() методу
        System.out.println("Gennady is containing = " + map2.containsKey(st24));

        /**
         * Если переопределить hasCode() и не переопределить equals(),
         * то мы так же не найдем объект
         */
    }

    private static class Student {
        private String name;
        private String surname;
        private int course;

        public Student(String name, String surname, int course) {
            this.name = name;
            this.surname = surname;
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            if (course != student.course) return false;
            if (!Objects.equals(name, student.name)) return false;
            return Objects.equals(surname, student.surname);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", course=" + course +
                    '}';
        }
    }

    private static class Student2 {
        private String name;
        private String surname;
        private int course;

        public Student2(String name, String surname, int course) {
            this.name = name;
            this.surname = surname;
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student2 student = (Student2) o;

            if (course != student.course) return false;
            if (!Objects.equals(name, student.name)) return false;
            return Objects.equals(surname, student.surname);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            result = 31 * result + course;
            return result;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", course=" + course +
                    '}';
        }
    }
}
