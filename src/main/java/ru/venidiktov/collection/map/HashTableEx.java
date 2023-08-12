package ru.venidiktov.collection.map;

import java.util.Hashtable;
import java.util.Objects;

/***
 * HashTable то же самое что и HashMap, но устаревшая и потокобезапасная,
 * в hashTable в качестве ключа и значения не может быть null!
 */
public class HashTableEx {
    public static void main(String[] args) {
        Hashtable<Integer, String> hashTable = new Hashtable<>();
        hashTable.put(100, "Maria");
        hashTable.put(100, "Iosif"); //Перезапишет 'Maria', тут нет коллизии так как ключи одинаковы
        hashTable.put(15, "Ivan");
        hashTable.put(33, "Petrov");
        System.out.println("HashTable = " + hashTable);

        Hashtable<Collision, String> hashtable2 = new Hashtable<>();
        hashtable2.put(new Collision("Petr"), "Ivanov");
        hashtable2.put(new Collision("Petr2"), "Ivanov");
        hashtable2.put(new Collision("Petr3"), "Ivanov");
        hashtable2.put(new Collision("Petr4"), "Ivanov");
        System.out.println("HashTable с коллизиями, все элементы хранятся в связном списке в одном бакете = " + hashtable2);
    }

    static class Collision {
        private String name;

        public Collision(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Collision collision = (Collision) o;

            return Objects.equals(name, collision.name);
        }

        @Override //Все будет складываться в один бакет
        public int hashCode() {
            return 5;
        }

        @Override
        public String toString() {
            return "Collision{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
