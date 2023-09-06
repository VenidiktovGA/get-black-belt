package ru.venidiktov.multithreading;

/**
 * Для запуска потока нужно использовать метод start,
 * если для запуска потока использовать метод run то новый поток не запустится а код из метода выполнится в том же потоке
 */
public class CallStartMethodNotCallRunMethod implements Runnable {
    public static void main(String[] args) {
        /**
         * Правильно запускаем поток
         */
//        System.out.println("Правильно запускаем поток");
//        System.out.println("Method main. Thread name " + Thread.currentThread().getName());
//        new Thread(new CallStartMethodNotCallRunMethod()).start();

        /**
         * Неправильно запускаем поток
         */
        System.out.println("Неправильно запускаем поток");
        System.out.println("Method main. Thread name " + Thread.currentThread().getName());
        new Thread(new CallStartMethodNotCallRunMethod()).run();
    }

    @Override
    public void run() {
        System.out.println("Method run. Thread name " + Thread.currentThread().getName());
    }
}
