package ru.venidiktov.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * При работе со стримами в Steram API по умолчанию ипользуются последовательные потоки!
 * Если нужно использовать параллельные потоки нужно это указать через Parallel stream!
 * При использовании Parallel stream java сама распределит работу над элементами потока по ядрам процессора
 * !!!Параллельное выполнение выгодно использовать при большом количестве элементов и когда нам не нужно выполнять очередность операций над элементами!!!
 */
public class ParallelStreamEx {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        list.add(10.0);
        list.add(5.0);
        list.add(1.0);
        list.add(0.25);

        /**
         * ЕСЛИ ЭЛЕМЕНТОВ МАЛО ТО ПАРАЛЛЕЛЬНОЕ ВЫПОЛНЕНИЕ БУДТ МЕДЛЕННЕ
         * нужно ведь тратить ресурсы на распараллеливание
         * Но чет по результатам не так!!!!!
         */
        System.out.println("ЕСЛИ ЭЛЕМЕНТОВ МАЛО ТО ПАРАЛЛЕЛЬНОЕ ВЫПОЛНЕНИЕ БУДТ МЕДЛЕННЕ");
        var startSerial = System.nanoTime();
        double sumSerial = list.stream().reduce((ac, el) -> ac + el).get();
        System.out.println("[Используем последовательный стрим]");
        System.out.println("Сумма = " + sumSerial);
        var resTimeSerial = System.nanoTime() - startSerial;
        System.out.println("Время = " + resTimeSerial);

        var startParallel = System.nanoTime();
        double sumParallel = list.parallelStream().reduce((ac, el) -> ac + el).get();
        System.out.println("[Используем параллельный стрим]");
        System.out.println("Сумма = " + sumParallel);
        var resTimeParallel = System.nanoTime() - startParallel;
        System.out.println("Время = " + resTimeParallel);
        System.out.println("Последовательное - Параллельное = " + (resTimeSerial - resTimeParallel)); //Даже на 5 элементах параллельное выполнение быстрее!!!
        System.out.println();

        /**
         * ЕСЛИ ЭЛЕМЕНТОВ МНОГО ТО ПАРАЛЛЕЛЬНОЕ ВЫПОЛНЕНИЕ БУДТ БЫСТРЕЕ
         * нужно ведь тратить ресурсы на распараллеливание
         */

        List<Double> list2 = new ArrayList<>();
        var random = new Random();
        for (int i = 0; i < 100_000_000; i++) {
            list2.add(random.nextDouble());
        }
        System.out.println("ЕСЛИ ЭЛЕМЕНТОВ МНОГО ТО ПАРАЛЛЕЛЬНОЕ ВЫПОЛНЕНИЕ БУДТ БЫСТРЕЕ");
        var startSerial2 = System.nanoTime();
        double sumSerial2 = list2.stream().reduce((ac, el) -> ac + el).get();
        System.out.println("[Используем последовательный стрим]");
        System.out.println("Сумма = " + sumSerial2);
        var resTimeSerial2 = System.nanoTime() - startSerial2;
        System.out.println("Время = " + resTimeSerial2);

        var startParallel2 = System.nanoTime();
        double sumParallel2 = list2.parallelStream().reduce((ac, el) -> ac + el).get();
        System.out.println("[Используем параллельный стрим]");
        System.out.println("Сумма = " + sumParallel2);
        var resTimeParallel2 = System.nanoTime() - startParallel2;
        System.out.println("Время = " + resTimeParallel2);
        System.out.println("Последовательное - Параллельное = " + (resTimeSerial2 - resTimeParallel2));
        System.out.println();
    }
}
