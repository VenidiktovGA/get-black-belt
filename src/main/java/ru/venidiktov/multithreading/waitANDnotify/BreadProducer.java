package ru.venidiktov.multithreading.waitANDnotify;

/**
 * Производитель хлеба
 */
public class BreadProducer implements Runnable {

    BreadStore breadStore;

    BreadProducer(BreadStore breadStore) {
        this.breadStore = breadStore;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            breadStore.put();
        }
    }
}