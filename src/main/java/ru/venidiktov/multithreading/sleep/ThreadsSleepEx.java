package ru.venidiktov.multithreading.sleep;

/**
 * Методы класса Thread
 * Thread - это класс представляющий концепцию потока (нити) в java
 * Можно усыпить поток на определенное время для этого используется метод sleep
 */
public class ThreadsSleepEx {
    public static void main(String[] args) {
        var thread0 = new Thread(new MyRunnable());
        var thread1 = new Thread(new MyRunnable());

        thread0.start();
        thread1.start();

        //Вначале в консоли мы увидим это сообщение, так как главный поток продолжит свое выполнение после создание дочерних потоков!
        System.out.println("Поток main продолжает работу после того как другие потоки од него отпачковались!");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", i = " + i);
            }
        }
    }
}
