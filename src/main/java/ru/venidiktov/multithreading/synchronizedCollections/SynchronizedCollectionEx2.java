package ru.venidiktov.multithreading.synchronizedCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Если мы попытаемся в разных потоках работать с не синхронизированной коллекцией,
 * например в одном потоке читать элементы а в другом удалять их то получим ConcurrentModificationException,
 * нежно использовать синхронизированные коллекции
 */
public class SynchronizedCollectionEx2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> list.add(i));

        /**
         * Работа нескольких потоков на не синхронизированной коллекцией
         * ! Операции чтения элементов в синхронизованных коллекциях не занимают лок - это значит что когда один поток чидает
         * данные из коллекции второй может ее менять, из за этого например при работе с iterator'ами может возникнуть ConcurrentModificationException
         */
        Runnable runnable1 = () -> {
            try {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println("Элемент в коллекции = " + iterator.next());
                }
            } catch (Exception e) {
                System.out.println("Получили исключение при работе нескольких потоков над не синхронизированной коллекцией");
                e.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try {
                list.remove(8);
            } catch (Exception e) {
                System.out.println("Получили исключение при работе нескольких потоков над не синхронизированной коллекцией");
                e.printStackTrace();
            }
        };
        Thread thread0 = new Thread(runnable1);
        Thread thread1 = new Thread(runnable2);
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
        Thread.sleep(100);

        /**
         * Работа нескольких потоков на синхронизированной коллекцией
         * ! Операции чтения элементов в синхронизованных коллекциях не занимают лок - это значит что когда один поток чидает
         * данные из коллекции второй может ее менять, из за этого например при работе с iterator'ами может возникнуть ConcurrentModificationException
         * что бы это избежать нужно самому синхронизировать код который работает с итераторами и подобным функционалом коллекций например метод size()
         */
        List<Integer> list2 = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> list2.add(i));
        List<Integer> syncList = Collections.synchronizedList(list2);
        Runnable runnable3 = () -> {
            synchronized (syncList) {
                Iterator<Integer> iterator = syncList.iterator();
                while (iterator.hasNext()) {
                    System.out.println("Элемент в коллекции = " + iterator.next());
                }
            }
        };
        Runnable runnable4 = () -> syncList.remove(8);

        Thread thread2 = new Thread(runnable3);
        Thread thread3 = new Thread(runnable4);
        thread2.start();
        thread3.start();
        thread2.join();
        thread3.join();
    }
}
