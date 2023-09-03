package ru.venidiktov.multithreading;

/**
 * В java для создания потока есть разные способы
 * 1
 * Создать свой класс и унаследовать им от класса Thread
 */
public class CreateThreadEx1 {
    public static void main(String[] args) {
        var myThread1 = new MyThread1();
        var myThread2 = new MyThread2();
        /**
         * 1 Хоть мы и запускаем потоки последовательно работать они будут параллельно!
         * 2 Между потоками нет синхронизации! Мы не знаем кто начнет выполняться первым!
         * 3 Во время запуска приложения запускается main поток от него создаются два наших потока,
         * пока все потоки приложения не завершат работу приложение продолжит работать!
         * 4 В классах мы переопределили метод run а вызываем методы start, так правильно, если мы вызываем метод start
         * jvm сама вызовет метод run (Вызывать метод run самому неправильно)
         */
        myThread1.start(); // Запускаем поток
        myThread2.start(); // Запускаем поток
    }

    /**
     * 1 - Создать свой класс и отнаследовать его от класса Thread,
     * переопределяем метод run в котором указываем код который нужно выполнить
     */
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            for (int i = 1; i < 1000; i++) {
                System.out.println("Экземпляр MyThread1, i = " + i);
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            for (int i = 1000; i > 1; i--) {
                System.out.println("Экземпляр MyThread2, i = " + i);
            }
        }
    }
}
