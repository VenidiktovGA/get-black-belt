package ru.venidiktov.multithreading.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * В java есть пакет java.util.concurrent.atomic который содержит классы предоставляющие атомарные операции,
 * но зачем, а затем что если операция над ресурсом с которым работает несколько потоков не атомарна то
 * разные потоки могут находиться на разной стадии этой операции над ресурсом и ресурс в итоге останется в не консистентном состоянии
 */
public class ForWhatAtomicClass {
    public static Integer count = Integer.valueOf(0);
    public static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("### Что бывает если разные потоки выполняют не атомарную операцию над общим ресурсом ###");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(0, 1000).forEach(i -> executorService.submit(new MyRunnable()));
        System.out.println("Ожидается результат 1000");
        System.out.println("Фактический результат " + count);

        System.out.println("### Используем атомарный класс в качестве общего ресурса ###");
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        IntStream.range(0, 1000).forEach(i -> executorService2.submit(atomicCount::incrementAndGet));
        System.out.println("Ожидается результат 1000");
        System.out.println("Фактический результат " + atomicCount);
    }

    private static class MyRunnable implements Runnable {

        /**
         * Как решение проблемы не атомарной операции это использовать synchronized,
         * а можно использовать атомарные классы, например Atomic integer!
         * synchronized работает медленно
         */
        @Override
        public void run() {
            /**
             * Операция увеличения на 1 значения не атомарна
             * 1 - Читаем текущее значение в переменной
             * 2 - Увеличиваем значение на 1
             * 3 - Записываем новое значение в переменную
             */
            count++;
        }
    }
}
