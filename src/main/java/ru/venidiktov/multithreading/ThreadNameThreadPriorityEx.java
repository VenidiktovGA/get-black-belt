package ru.venidiktov.multithreading;

/**
 * Методы класса Thread
 * Thread - это класс представляющий концепцию потока (нити) в java
 * Создаваемым потокам можно давать имена и назначать приоритет (по умолчанию 5)
 */
public class ThreadNameThreadPriorityEx {
    public static void main(String[] args) {

        /**
         * Имя потока и приоритет по умолчанию
         */
        var myThread1 = new MyThread();
        System.out.println("Имя потока 1 по умолчанию = " + myThread1.getName());
        System.out.println("Приоритет потока 1 по умолчанию = " + myThread1.getPriority());
        var myThread2 = new MyThread();
        System.out.println("Имя потока 2 по умолчанию = " + myThread2.getName());
        System.out.println("Приоритет потока 2 по умолчанию = " + myThread2.getPriority());

        /**
         * Можно задать имя потока и его приоритет
         */
        var myThread3 = new MyThread();
        myThread3.setName("Cool thread");
        myThread3.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Имя потока 3 (заданное) = " + myThread3.getName());
        System.out.println("Приоритет потока 3 (заданное) = " + myThread3.getPriority());
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            // Thread.currentThread() - выдает имя текущего потока!
            System.out.println("Hello i'm " + Thread.currentThread());
        }
    }
}
