package ru.venidiktov.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

/***
 * LikedList имплементирует интерфейс Deque который имплементирует интерфейс Queue,
 * так что мы можем рассмотреть LinkedList как однанаправленную или двунаправленную очередь
 * ТУТ РАССМАТРИВАЕМ LinkedList как ОДНАНАПРАВЛЕННУЮ ОЧЕРЕДЬ
 */
public class LinkedListEx {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        // add() элементы в очередь встают в конец, если мы добавляем большее количества элементов чем размер очереди то элемент просто не добавится
        // метод offer() выбросит исключение при добавлении большего количества элементов чем размер очереди
        queue.add("Maria");
        queue.add("Ivan");
        queue.add("Iosif");
        queue.add("Lena");
        System.out.println("queue = " + queue);

        // remove() если попробовать удалить больше чем есть будет exception
        // poll() при попытке удалить больше чем есть НЕ будет exception
        System.out.println("Remove element = " + queue.remove());
        System.out.println("queue after remove() without parameters = " + queue);

        // element() если попробовать вывести элемент, а очередь пуста то будет exception
        // peek() делает то же самое что и element() но не выкидывает exception
        System.out.println("First element in queue = " + queue.element());

        /***
         * Мы можем удалить элемент из середины очереди,
         * но это нарушает смысл очереди!!!
         */
        queue.remove("Iosif");
        System.out.println("queue after remove element from middle = " + queue);
    }
}
