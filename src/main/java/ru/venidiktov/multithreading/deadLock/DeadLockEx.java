package ru.venidiktov.multithreading.deadLock;

/**
 * DeadLock - мертвая блокировка, возникает когда поток А захватил ресурс r1 и ждет ресурс r2
 * в свою очередь поток B захватил ресурс r2 и ждет ресурс r1,
 * в данной ситуации потоки заблокировали ресурсы необходимые друг другу для продолжения работы и потоки просто бесконечно ждут
 */
public class DeadLockEx {
    public static final Object lock1 = new MyLock("lock1");
    public static final Object lock2 = new MyLock("lock2");

    /**
     * DeadLock не всегда может случится!
     */
    public static void main(String[] args) {
        var thread0 = new Thread(new MyThread1());
        var thread1 = new Thread(new MyThread2());
        thread0.start();
        thread1.start();

        /**
         * Для того чтоб ы Deadlock не возник оба потока должны занимать мониторы объекта в одинаковом порядке,
         * тогда если какой-то поток заблокировал монитор первого объекта то второй будет ждать освобождения этого монитора
         * и не будет блокировать монитор другого объекта!
         */

    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s пытается захватить монитор объекта %s", Thread.currentThread().getName(), lock1));
            synchronized (DeadLockEx.lock1) {
                System.out.println(String.format("Поток %s захватил монитор объекта %s", Thread.currentThread().getName(), lock1));

                System.out.println(String.format("Поток %s пытается захватить монитор объекта %s", Thread.currentThread().getName(), lock2));
                synchronized (DeadLockEx.lock2) {
                    System.out.println(String.format("Поток %s захватил монитор объекта %s", Thread.currentThread().getName(), lock2));
                    System.out.println(String.format("Поток %s захватил мониторы объектов %s и %s", Thread.currentThread().getName(), lock1, lock2));

                }
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s пытается захватить монитор объекта %s", Thread.currentThread().getName(), lock2));
            synchronized (DeadLockEx.lock2) {
                System.out.println(String.format("Поток %s захватил монитор объекта %s", Thread.currentThread().getName(), lock2));

                System.out.println(String.format("Поток %s пытается захватить монитор объекта %s", Thread.currentThread().getName(), lock1));
                synchronized (DeadLockEx.lock1) {
                    System.out.println(String.format("Поток %s захватил монитор объекта %s", Thread.currentThread().getName(), lock1));
                    System.out.println(String.format("Поток %s захватил мониторы объектов %s и %s", Thread.currentThread().getName(), lock2, lock1));

                }
            }
        }
    }

    private static class MyLock {
        private String name;

        public MyLock(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyLock{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
