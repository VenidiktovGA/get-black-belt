package ru.venidiktov.multithreading.concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue реализует интерфейс BlockingQueue что позволяет потокам ждать прежде чем добавить, интерфейс
 * BlockingQueue обязывает реализовать методы для работы с очередью в многопоточном режиме:
 * take(): Возвращает верхний элемент и удаляет его из очереди. Если очередь пуста, метод будет ждать, пока элемент не станет доступен в очереди.
 * poll(timeout,unit): Возвращает головной элемент и удаляет его из очереди. Если очередь пуста, метод будет ждать,
 * пока элемент будет доступен в течение указанного промежутка времени. Если время ожидания заканчивается без доступных элементов, метод вернет значение null.
 * put(e): Вставить элемент в очередь. Если очередь заполнена, этот метод будет ждать, пока не появится свободное место для вставки.
 * offer(e,timeout,unit):  Вставить элемент в очередь. Если очередь заполнена, метод будет ждать,
 * пока освободится место для вставки в течение указанного промежутка времени. Если тайм-аут заканчивается без свободного места,
 * никаких действий предпринято не будет, и метод вернет значение false.
 */
public class ArrayBlockingQueueEx {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);

        Runnable producer = () -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Помещаем в очередь элемент: " + i);
                    /**
                     * Вставить элемент в очередь. Если очередь заполнена, этот метод будет ждать, пока не появится свободное место для вставки.
                     */
                    queue.put(i);
                    System.out.println("Очередь: " + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(3000);
                    /**
                     * Возвращает верхний элемент и удаляет его из очереди. Если очередь пуста, метод будет ждать, пока элемент не станет доступен в очереди.
                     */
                    System.out.println("Забираем из очереди элемент: " + queue.take());
                    System.out.println("Очередь: " + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var producerThread = new Thread(producer);
        var consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }
}
