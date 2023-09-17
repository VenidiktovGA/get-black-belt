package ru.venidiktov.multithreading.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Используем 10 потоков, чтоб досчитать сумму от 1 до 1_000_000_000
 */
public class CountToABillion {
    private static final long VALUE = 1_000_000_000L;
    private static long sum = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> futures = new ArrayList<>();
        long valueDividedBy10 = VALUE / 10;

        for (int i = 0; i < 10; i++) {
            long from = (valueDividedBy10 * i) + 1;
            long to = valueDividedBy10 * (i + 1);
            MySum mySum = new MySum(from, to);
            Future<Long> futurePartSum = executorService.submit(mySum);
            futures.add(futurePartSum);
        }
        sum = futures.stream().mapToLong(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Не удалось получить часть суммы");
            }
        }).sum();

        executorService.shutdown();
        System.out.println(String.format("#### Результат сложения всех чесле от 1 до 1_000_000_000 = %s ####", sum));
    }

    private static class MySum implements Callable<Long> {
        private long sum;

        private long from;

        private long to;

        public MySum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            System.out.println(String.format("Поток %s начал", Thread.currentThread().getName()));
            for (long i = from; i <= to; i++) {
                sum += i;
            }
            System.out.println(String.format("Сумма от %s до %s = %s", from, to, sum));
            System.out.println(String.format("Поток %s закончил", Thread.currentThread().getName()));
            return sum;
        }
    }
}
