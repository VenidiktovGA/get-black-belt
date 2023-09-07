package ru.venidiktov.multithreading.join;

/**
 * Методы класса Thread
 * Thread - это класс представляющий концепцию потока (нити) в java
 * Для того чтобы сказать одному потоку что он должен подождать выполнение другого потока и после него рподолжить выполнение используется метод join
 */
public class ThreadJoinEx1 {
    public static void main(String[] args) throws InterruptedException {
        var thread0 = new Thread(new MyRunnable());
        var thread1 = new Thread(new MyRunnable());

        thread0.start();
        thread1.start();

        /**
         * Поток в котором вызывается метод join ждет поток на котором вызывается метод join,
         * в данном случаем мы вызываем метод join в потоке main, поток на котором вызывается метод join это htread0
         * => поток main ждет поток thread0 ну и thread1
         */
        thread0.join();
        thread1.join();

        //Данное сообщение мы увидим в конце, так как вызвали методы join для наших дочерних потоков в потоке main!
        System.out.println("Благодоря методу join главный поток (main) ждет выполнения дочерних потоков!");
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
