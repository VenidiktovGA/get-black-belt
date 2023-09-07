package ru.venidiktov.multithreading.join;

/**
 * Методы класса Thread
 * Thread - это класс представляющий концепцию потока (нити) в java
 * Для того чтобы сказать одному потоку что он должен подождать выполнение другого потока и после него рподолжить выполнение используется метод join
 */
public class ThreadJoinEx2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " start!");
        var thread0 = new Thread(new Worker());

        thread0.start();

        /**
         * Поток в котором вызывается метод join ждет поток на котором вызывается метод join,
         * в данном случаем main поток ждет пока поток thread0 не завершит свою работу или пока не пройдет 1.5 сек
         * тут thread0 выполняется дольше чем 1.5 сек
         */
        thread0.join(1500);

        System.out.println(Thread.currentThread().getName() + " end!");
    }

    private static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start!");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end!");
        }
    }
}
