package ru.venidiktov.multithreading.callable;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ! Callable<V> появился в java 5 !
 * <p>
 * Интерфейс Callable<V> очень похож на интерфейс Runnable. Объекты, реализующие данные интерфейсы,
 * исполняются другим потоком. Однако, в отличие от Runnable, интерфейс Callable использует Generic'и
 * для определения типа возвращаемого объекта. Runnable содержит метод run(),
 * описывающий действие потока во время выполнения, а Callable – метод call().
 * Класс Executors предоставляет полезные методы для выполнения Callable в пуле потоков.
 * Callable таски (задачи) возвращают java.util.concurrent.Future объект.
 * Используя Future мы можем узнать статус Callable таска и получить возвращенный объект.
 * Это обеспечивает get() метод, который ждет завершение Callable, чтобы вернуть результат.
 * <p>
 * Интерфейс Future представляет собой результат работы Callable из метода submit(),
 * это результат из будущего чтоб его получить используется метод get(), метод get() блокирует поток гед он вызывается
 * пока не будет получен результат Callable, Есть перегруженный вариант метода get(),
 * где мы можем указать время ожидания результата, это нужно для избежания блокировки текущего потока на длительное время.
 */
public class CallableEx {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //submit() возвращает Future<V>
        /**
         * ! Над Runnable то же можно передать в submit() но для него Future всегда будет возвращать Null !
         *
         * ! Callable можно использовать только с ExecutorService а с отдельным создание Thread нельзя !
         */
        Future<Integer> f5 = executorService.submit(new Factorial(5)); // С Callable используем метод submit()
        Integer resultF5 = 0;
        try {
            /**
             * Метод get() позволяет получить результат задания из Callable, может произойти ExecutionException
             * Метод get() блокирует выполнение потока в котором он вызывается то тех пор пока метод submit() не вернет Future
             */
            System.out.println(String.format("Хотим получить результат работы callable, время - %s", LocalTime.now().truncatedTo(ChronoUnit.SECONDS)));
            System.out.println(String.format("Закончилась выполнение задания (Callable) - %s", f5.isDone()));
            resultF5 = f5.get();
            System.out.println(String.format("Получили результат работы callable, время - %s", LocalTime.now().truncatedTo(ChronoUnit.SECONDS)));
            System.out.println(String.format("Закончилась выполнение задания (Callable) - %s", f5.isDone()));
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        } finally {
            executorService.shutdown();
        }
        // awaitTermination() тут использовать не нужно, так как метод get() у Future блокирует поток в котором он вызывается и к моменту когда Main поток продолжится результат уже будте
//        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Факториал 5! = " + resultF5);
    }

    private static class Factorial implements Callable<Integer> {

        private int f;

        public Factorial(int f) {
            this.f = f;
        }

        @Override
        public Integer call() throws Exception {
            if (f <= 0) {
                throw new IllegalArgumentException("Введено неверное число");
            } else {
                int result = 1;
                for (int i = 1; i <= f; i++) {
                    Thread.sleep(1000);
                    result *= i;
                }
                return result;
            }
        }
    }
}
