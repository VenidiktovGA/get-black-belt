package ru.venidiktov.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pool - это некоторый готовый набор потоков который создан заранее, потоки из этого набора
 * выполняют задачи которые появляются, когда задача выполнена поток снова может выполнять другую задачу,
 * тем самым мы не тратим время и ресурсы на дорогостоящие операции создания и удаления потоков
 * <p>
 * newCachedThreadPool() создает пул для выполнения задач, потоки в данном пуле создаются и удаляются по мере поступления и выполнения задач,
 * например приходит задание создается поток, если приходит еще одно задание и нет свободных потоков то создается новый поток,
 * если поток выполнил звдачу и ему не приходило задач в течении 60 секунд то поток уничтожется
 */
public class NewCachedThreadPoolEx {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток запустился");
        /**
         * C ThreadPool обычно работают через ExecutorService
         * Обычно ThreadPool создают через фабричные методы Executors (не напрямую через new)
         *
         * Ниже мы создали пул потоков и выдали ему задания (класс реализующий интерфейс Runnable)
         * Потоку из пула назначаются задания, как только поток занят ждем,
         * как только поток освободился ему назначается новое задание
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * Выдаем задачи, и если все потоки заняты под задачу создается новый поток,
         * если поток не востребован в течении 60 секунд он удаляется из пула
         */
        executorService.execute(new MyRunnable());
        Thread.sleep(500);
        executorService.execute(new MyRunnable());
        Thread.sleep(500);
        executorService.execute(new MyRunnable());
        Thread.sleep(500);

        /**
         * Если не остановить све потоки в ThreadPool то поток с пулом потоков не остановится пока не будут удалены все потоки,
         * в нашем случае это произойдет когда с момента выполнения задачи самым молодым потоком пройдет 60 сеунд и он не удалится,
         * тем самым в пуле не будет потоков так как нет задач
         */
//        executorService.shutdown();
        System.out.println("Main поток остановлен");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("Поток %s начал работу", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
