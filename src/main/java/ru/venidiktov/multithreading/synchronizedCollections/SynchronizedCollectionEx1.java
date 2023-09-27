package ru.venidiktov.multithreading.synchronizedCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Синхронизованные коллекции
 * Синхронизованные коллекции полученные как обертки над обычными не синхронизованными коллекциями с использованием
 * блока synchronized в каждом методе коллекции
 */
public class SynchronizedCollectionEx1 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> source = new ArrayList<>();
        source.add(1);

        /**
         * Не синхронизованная коллекция
         */
        List<Integer> notSynchronizedList = new ArrayList<>();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> executorService1.submit(() -> notSynchronizedList.addAll(source)));
        System.out.println("Результат работы нескольких потоков над не синхронизованной коллекцией");
        System.out.println("будет не предсказуем, в коллекции будет разное количество элементов (Должно быть 10)");
        System.out.println(notSynchronizedList + " - результат");
        System.out.println("[1, 1, 1, 1, 1, 1, 1, 1, 1, 1] - ожидается");
        System.out.println();

        /**
         * Синхронизованная коллекция через обертку
         */
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> executorService2.submit(() -> synchronizedList.addAll(source)));
        System.out.println("Результат работы нескольких потоков над синхронизованной коллекцией");
        System.out.println("результат будет всегда одинаков, 10 элементов (Должно быть 10)");
        System.out.println(synchronizedList + " - результат");
        System.out.println("[1, 1, 1, 1, 1, 1, 1, 1, 1, 1] - ожидается");

    }
}
