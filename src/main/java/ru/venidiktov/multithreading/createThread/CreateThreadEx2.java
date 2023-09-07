package ru.venidiktov.multithreading.createThread;

/**
 * В java для создания потока есть разные способы
 * 2
 * Использовать Lambda интерфейс Runnable [Данный вариант часто используется],
 * актуально если наш класс уже унаследован от какого то класса то и следовательно он не может относледоватся от класса Thread
 */
public class CreateThreadEx2 {
    public static void main(String[] args) {
        /**
         * 0 СОЗДАЕМ ОБЪЕКТ THREAD И ПЕРЕДАДИМ ЕМУ КАК АРГУМЕНТ НАШ КЛАСС РЕАЛИЗУЮЩИЙ LAMBDA ИНТЕРФЕЙС RUNNABLE
         * 1 Хоть мы и запускаем потоки последовательно работать они будут параллельно!
         * 2 Между потоками нет синхронизации! Мы не знаем кто начнет выполняться первым!
         * 3 Во время запуска приложения запускается main поток от него создаются два наших потока,
         * пока все потоки приложения не завершат работу приложение продолжит работать!
         * 4 В классах мы переопределили метод run а вызываем методы start, так правильно, если мы вызываем метод start
         * jvm сама вызовет метод run (Вызывать метод run самому неправильно)
         */
        var thread1 = new Thread(new MyThread1());
        var thread2 = new Thread(new MyThread2());
        thread1.start();
        thread2.start();
    }

    /**
     * 2 - Имплементировать Lambda интерфейс Runnable,
     * переопределяем метод run в котором указываем код который нужно выполнить
     */
    private static class MyThread1 implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 1000; i++) {
                System.out.println("Экземпляр MyThread1, i = " + i);
            }
        }
    }

    private static class MyThread2 implements Runnable {
        @Override
        public void run() {
            for (int i = 1000; i > 1; i--) {
                System.out.println("Экземпляр MyThread2, i = " + i);
            }
        }
    }
}
