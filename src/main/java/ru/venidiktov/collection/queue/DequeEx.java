package ru.venidiktov.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * Двунаправленная очередь, реализации LinkedList и ArrayDeque
 */
public class DequeEx {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(10);
        deque.offerFirst(2);
        deque.offerLast(9);

        System.out.println("Deque = " + deque);

        System.out.println("remove first = " + deque.removeFirst());
        System.out.println("Deque = " + deque);

        System.out.println("remove last = " + deque.pollLast());
        System.out.println("Deque = " + deque);

        System.out.println("element first = " + deque.getFirst());
        System.out.println("element last = " + deque.peekLast());
        System.out.println("Просто метод peek() = " + deque.peek());
    }
}
