package ru.venidiktov.collection.map;

import java.util.HashMap;
import java.util.Map;

/***
 * HashMap
 * Ключи уникальны, а значения нет
 * В качестве ключа и значения можно использовать Null
 */
public class HashMapEx {
    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(100, "Maria");
        map1.put(15, "Ivan");
        map1.put(33, "Petrov");
        map1.put(null, "Ivan");
        map1.put(1, null);
        System.out.println("map1 = " + map1);

        //putIfAbsent - Добавляет элемент если такого элемента в мапе нет
        map1.putIfAbsent(33, "Iosif");
        System.out.println("Map after putIfAbsent (33) = " + map1);

        //get
        System.out.println("Element by key 100 = " + map1.get(100));

        //remove
        map1.remove(33);
        System.out.println("Map after remove element by key 33 = " + map1);

        //containsValue
        System.out.println("Map contains value 'Petrov' = " + map1.containsValue("Petrov"));

        //containsKey
        System.out.println("Map contains key '1' = " + map1.containsKey(1));

        //keySet
        System.out.println("key set for map1 = " + map1.keySet());

        //values
        System.out.println("Values for map1 = " + map1.values());

        //entrySet
        System.out.println("Entry set for map1 = " + map1.entrySet());

        /**
         * capacity - задает начальный размер массива с корзинами
         * load factor - значение которое отражает уровень заполненности мапы для увеличения ее размера в двое
         * При увеличении размера мапы будет произведен rehash пересчитается hash для каждого элемента,
         * возможно элементы пере размешаться
         */
        Map<Integer, Integer> map2 = new HashMap<>(8, 0.2f);

        /***
         * В качестве ключей map нужно использовать не мутабельный объект,
         * иначе при изменении объекта его hashCode может изменится и мы не найдем значение под ключом
         */
        System.out.println("#######################");
        Map<Student, Integer> map3 = new HashMap<>();
        var st3 = new Student("Ivan", "Ivanod", 25);
        map3.put(st3, 5);
        System.out.println("map3 contains st3 = " + map3.containsKey(st3));
        System.out.println("hash for st3 = " + st3.hashCode());
        st3.setAge(26);
        System.out.println("map3 contains st3 = " + map3.containsKey(st3));
        System.out.println("hash for st3 = " + st3.hashCode());


    }

    public static class Student {
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
            result = 31 * result + age;
            return result;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
