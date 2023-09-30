package ru.venidiktov.multithreading.concurrentCollections;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList это коллекция потокобезопасность которого основанна на алгоритме CopyOnWrite
 * то есть, кгда в лист добавляется элемент через set() или add() делается клонированная копия листа в нее добавляется новый элемент
 * и на выходе мы получаем клонированную копию листа с новыми элементами
 * Операция удаления у итератора CopyOnWriteArrayList не поддерживается!
 */
public class CopyOnWriteArrayListEx {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Iosif");
        list.add("Maria");
        list.add("Alex");

        Runnable runnable1 = () -> {
            /**
             * Iterator в CopyOnWriteArrayList работает с копией листа созданной в момент создания итератора,
             * поэтому данный итератор не увидит новые элементы добавленные в лист после создания итератора
             * и соответственно не случится ConcurrentModificationException
             */
            Iterator<String> iterator = list.iterator(); // Создается копия листа для итератора
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Элемент " + iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove("Alex"); // Создается копия исходного листа, копия становится исходным листом
            list.add("Alexander"); // Создается копия исходного листа, копия становится исходным листом
        };

        var thread0 = new Thread(runnable1);
        var thread1 = new Thread(runnable2);
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
        System.out.println("Конечный список элементов = " + list);
    }
}
