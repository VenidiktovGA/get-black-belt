package ru.venidiktov.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool - это некоторый готовый набор потоков который создан заранее, потоки из этого набора
 * выполняют задачи которые появляются, когда задача выполнена поток снова может выполнять другую задачу,
 * тем самым мы не тратим время и ресурсы на дорогостоящие операции создания и удаления потоков
 * <p>
 * newFixedThreadPool(int nThreads) создает пул на указанное число потоков. Если новые задачи добавлены,
 * когда все потоки активны, то они будут сохранены в очереди для выполнения позже.
 * Если один из потоков завершился из-за ошибки, на его место будет запущен другой поток.
 * Потоки живут до тех пор, пока пул не будет закрыт явно методом shutdown()
 */
public class NewFixedThreadPoolEx {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток запустился");
        /**
         * C ThreadPool обычно работают через ExecutorService
         * Обычно ThreadPool создают через фабричные методы Executors (не напрямую через new)
         *
         * Ниже мы создали пул потоков с 5 потоками и выдали им задания (класс реализующий интерфейс Runnable)
         * Всем потокам из пула назначаются задания, как только свободных потов неосталось ждем,
         * как только поток освободился ему назначается новое задание
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
                Thread.sleep(5100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
