package ru.venidiktov.multithreading.sleep;

/**
 * Методы класса Thread
 * Thread - это класс представляющий концепцию потока (нити) в java
 * Можно усыпить поток на определенное время для этого используется метод sleep
 */
public class MainThreadSleepEx {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Усыпляем главный поток (main)
         */
        for (int i = 5; i > 0; i--) {
            System.out.println("i = " + i);
            Thread.sleep(1000);
        }
        System.out.println("Готово!");
    }
}
