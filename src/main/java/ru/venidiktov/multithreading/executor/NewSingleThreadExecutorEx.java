package ru.venidiktov.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool - это некоторый готовый набор потоков который создан заранее, потоки из этого набора
 * выполняют задачи которые появляются, когда задача выполнена поток снова может выполнять другую задачу,
 * тем самым мы не тратим время и ресурсы на дорогостоящие операции создания и удаления потоков
 * <p>
 * newSingleThreadExecutor() создает пул на 1 поток.
 */
public class NewSingleThreadExecutorEx {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток запустился");
        /**
         * C ThreadPool обычно работают через ExecutorService
         * Обычно ThreadPool создают через фабричные методы Executors (не напрямую через new)
         *
         * Ниже мы создали пул потоков с 1 потоком и выдали ему задания (класс реализующий интерфейс Runnable)
         * Потоку из пула назначаются задания, как только поток занят ждем,
         * как только поток освободился ему назначается новое задание
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 10; i++) {
            executorService.execute(new MyRunnable());
        }
        /**
         * Если не остановить све потоки в ThreadPool то поток с пулом потоков не остановится и будет ждать новых задач,
         * для остановки пула потоков можно вызвать метод shutdown()
         */
        executorService.shutdown();
        /**
         * awaitTermination() метод подобен методу join()
         * awaitTermination() используют после вызова shutdown()
         * awaitTermination() говорит что поток где был вызван данный метод ждет выполнения всех задач в ThreadPool
         * или пока не истечет время указанное в аргументе метода awaitTermination()
         */
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Main поток остановлен");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s начал работу", Thread.currentThread().getName()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
