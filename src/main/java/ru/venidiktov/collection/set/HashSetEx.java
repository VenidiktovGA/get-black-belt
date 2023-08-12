package ru.venidiktov.collection.set;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/***
 * HashSet работает на основе HashMap, просто вместо значений во всех парах хранятся контстантные значения
 */
public class HashSetEx {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Ivan");
        set.add("Maria");
        set.add("Ivan");
        set.add(null);
        System.out.println("set = " + set);
        // Методы унаследованные от интерфейса Collection
        System.out.println("set size = " + set.size());
        System.out.println("set after remove null = " + set.remove(null));
        System.out.println("set is empty = " + set.isEmpty());
        System.out.println("set contains 'T-rex' = " + set.contains("T-rex"));

        /***
         * В отличие от Mas в Set нет метода get так как Set подразумевает что в нем хранятся только значения, а не пара <K, V>,
         * метода get нет так как при разработке Set больше ориентировались на математическую модель чем на практику,
         * а в математике множеству не особо нужен метод get()
         * Как же тогда найти элемент в Set? Вот так например
         */
        for (String elem : set) {
            if (elem.equals("Maria")) {
                System.out.println("Мы нашли 'Maria' в множестве set = " + set);
                break;
            }
        }
        for (String elem : set) {
            if (elem.equals("T-rex")) {
                System.out.println("Мы нашли 'T-rex' в множестве set = " + set);
                break;
            }
        }

        /***
         * HashSet на основе HashMap значит для сравнения ключей он использует сначала hashCode() а затем equals()
         */
        // В Students age не участвует в hashCode() но участвует в equals()
        Set<Student> set2 = new HashSet<>();
        set2.add(new Student("Maria", "Ivanova", 25)); // Добавится
        set2.add(new Student("Maria", "Ivanova", 25)); // Не добавится
        set2.add(new Student("Maria", "Ivanova", 22)); // Добавится
        System.out.println("hash set with collision = " + set2);
    }

    private static class Student {
        private String name;
        private String surname;
        private int age;

        public Student(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            if (age != student.age) return false;
            if (!Objects.equals(name, student.name)) return false;
            return Objects.equals(surname, student.surname);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
