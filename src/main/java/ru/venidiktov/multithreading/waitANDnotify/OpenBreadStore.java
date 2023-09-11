package ru.venidiktov.multithreading.waitANDnotify;

/**
 * Пусть производитель должен произвести 5 товаров, соответственно потребитель должен их все купить.
 * Но при этом одновременно на складе может находиться не более 3 товаров.
 */
public class OpenBreadStore {
    public static void main(String[] args) {
        var breadStore = new BreadStore();
        /**
         * Синхронизация методов get() pu() в объекте BreadStore() идет по this
         */
        var breadProducer = new Thread(new BreadProducer(breadStore));
        var breadConsumer = new Thread(new BreadConsumer(breadStore));

        breadProducer.start();
        breadConsumer.start();
    }
}
