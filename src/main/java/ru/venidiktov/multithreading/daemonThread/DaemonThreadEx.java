package ru.venidiktov.multithreading.daemonThread;

/**
 * Daemon thread - это фоновый процесс который обычно нужен для выполнения фоновых процессов для пользовательских потоков,
 * например сборка мусора.
 */
public class DaemonThreadEx {
    public static void main(String[] args) {
        /**
         * Запускаем 3 потока: Main, User, Daemon
         * Тут Main поток остановится раньше всех, далее JVM ждет завершения User потока и не ждет завершения Daemon потока!
         */
        System.out.println(String.format("Main поток %s запушен", Thread.currentThread().getName()));
        var userThread = new UserThread();
        var daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();
        System.out.println(String.format("Main поток %s остановлен", Thread.currentThread().getName()));
    }

    private static class UserThread extends Thread {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s это Daemon поток: %s", Thread.currentThread().getName(), Thread.currentThread().isDaemon()));
            for (char i = 'A'; i <= 'J'; i++) {
                try {
                    Thread.sleep(300);
                    System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s это Daemon поток: %s", Thread.currentThread().getName(), Thread.currentThread().isDaemon()));
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
