package ru.venidiktov.multithreading.waitANDnotify;

/**
 * Покупатель хлеба
 */
public class BreadConsumer implements Runnable {

    BreadStore breadStore;

    BreadConsumer(BreadStore breadStore) {
        this.breadStore = breadStore;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            breadStore.get();
        }
    }
}