package ru.venidiktov.multithreading.synchronizedEx;

/**
 * Synchronized
 * Data dace - состояние гонки, это когда несколько потоков конкурируют за один ресурс и изменяют его,
 * изменения ресурса зависит от его предыдущего состояния так же операции над ресурсом которые делают потоки не атомарны,
 * тес самым результат не предсказуем!!!
 * Для решения проблемы гонки можно использовать синхронизированные методы или атомарные переменные
 */
public class SynchronizedMethodEx {
    public static void main(String[] args) throws InterruptedException {
        var thread0 = new Thread(new MyRunnableImpl1());
        var thread1 = new Thread(new MyRunnableImpl1());
        var thread2 = new Thread(new MyRunnableImpl1());
        thread0.start();
        thread1.start();
        thread2.start();
        thread0.join();
        thread1.join();
        thread2.join();
        System.out.println("Ожидается что в конце программы count будет равен 3000");
        System.out.println("В конце работы программы значение переменной count = " + Counter.count);
    }

    /**
     * Синхронизированный метод, для синхронизации используется монитор объекта DataRaceSynchronizedMethodEx
     */
    public static synchronized void increment() {
        Counter.count++;
    }

    private static class Counter {
        static Integer count = 0;
    }

    private static class MyRunnableImpl1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        }
    }
}
