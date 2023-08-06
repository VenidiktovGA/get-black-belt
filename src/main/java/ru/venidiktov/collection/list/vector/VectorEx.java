package ru.venidiktov.collection.list.vector;

import java.util.Vector;

/***
 * Vector устаревшая synchronized коллекция, не рекомендовано к использованию!
 * Вместо Vector лучше использовать ArrayList или LinkedList
 * Если нужна потокобезопасная коллекция то лучше использовать другие коллекции а не Vector
 */
public class VectorEx {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("Maria");
        vector.add("Iosif");
        vector.add("Gennady");
        System.out.println("First element of vector = " + vector.firstElement());
        System.out.println("Last element of vector = " + vector.lastElement());
        vector.remove(2);
        System.out.println("vector = " + vector);
        System.out.println("Element with index 1 = " + vector.get(1));
    }
}
