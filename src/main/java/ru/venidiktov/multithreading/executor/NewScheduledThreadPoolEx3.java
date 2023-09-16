package ru.venidiktov.multithreading.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool - это некоторый готовый набор потоков который создан заранее, потоки из этого набора
 * выполняют задачи которые появляются, когда задача выполнена поток снова может выполнять другую задачу,
 * тем самым мы не тратим время и ресурсы на дорогостоящие операции создания и удаления потоков
 * <p>
 * newScheduledThreadPool() создает пул для выполнения задач через указанное время или переодически.
 */
public class NewScheduledThreadPoolEx3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток запустился");
        /**
         * C ThreadPool обычно работают через ExecutorService
         * Обычно ThreadPool создают через фабричные методы Executors (не напрямую через new)
         *
         * Ниже мы создали пул потоков с 5 потоком и выдали ему задания (класс реализующий интерфейс Runnable)
         * Потоку из пула назначаются задания, как только поток занят ждем,
         * как только поток освободился ему назначается новое задание
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        for(int i = 1; i < 10; i ++) {
//            scheduledExecutorService.execute(new MyRunnable()); // Тут метода execute() работает как и в newSingleThreadExecutor, он просто сразу запустит задачи в потока
//        }
        /**
         * Говорим что через 3 секунды начнется периодический запуск задач с периодом в 2 секунду
         * Период - это время между концом одной задачи и началом другой
         */
        scheduledExecutorService.scheduleWithFixedDelay(new MyRunnable(), 3, 1, TimeUnit.SECONDS);

        /**
         * Если не остановить све потоки в ThreadPool то поток с пулом потоков не остановится и будет ждать новых задач,
         * для остановки пула потоков можно вызвать метод shutdown()
         *
         * Тут мы ставим задержку в потоке Main чтобы он сразу не вызвал shutdown() и не остановил ThreadPool,
         * а то мы не увидим периодический запуск задач
         * !!! Если задача выполняется больше времени чем период запуска задач, то ThreadPool подождет пока этот поток завершит задачу !!!
         */
        Thread.sleep(20000);
        scheduledExecutorService.shutdown();
        System.out.println("Main поток остановлен");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s начал работу", Thread.currentThread().getName()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
