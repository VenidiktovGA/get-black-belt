package ru.venidiktov.multithreading.synchronizedEx;

/**
 * Synchronized
 * Для решения проблемы гонки потоков за один ресурс используется ключевое слово synchronized
 * Synchronized использует монитор объекта или класса (для статических методов) для одновременного доступа только 1 потока в блок кода
 * Монитор это mutex с некоторым инфраструктурным кодом по его занятию и освобождению
 */
public class SynchronizedBlockEx {
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
     * Синхронизованный блок кода, в качестве монитора для синхронизации использую статический класс Counter,
     * получается все потоки для синхронизации используют один и тот же монитор и они все синхронизированы
     */
    public static void increment() {
        synchronized (Counter.class) {
            Counter.count++;
        }
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
