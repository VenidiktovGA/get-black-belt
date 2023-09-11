package ru.venidiktov.multithreading.waitANDnotify;

/**
 * Хлебный магазин
 * wait() и notify() вызываются только из блока synchronize
 * wait() освобождает монитор и усыпляет поток на котором был вызван метод wait()
 * notify() не освобождает монитор для текущего потока, происходит рандомный подъем потока из сна который был усыплен вызовом метода wait()
 */
class BreadStore {
    private int product = 0;

    public synchronized void get() {
        /**
         * !!!!
         * Когда поток проснется под воздействием метода notify() после сна от wait() он продолжит выполнение с то го же места где уснул
         * следовательно нужно снова проверить условие. Если использовать if условие не проверится и код пойдет дальше!!!!
         */
        while (product < 1) {
            try {
                System.out.println("#### " + Thread.currentThread().getName() + " ждет!");
                wait();
                System.out.println("#### " + Thread.currentThread().getName() + " проснулся!");
            } catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("#### " + Thread.currentThread().getName() + " взял 1 товар!");
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        System.out.println("#### " + Thread.currentThread().getName() + " будит другой поток!");
        notify();
    }

    public synchronized void put() {
        while (product >= 3) {
            try {
                System.out.println("#### " + Thread.currentThread().getName() + " ждет!");
                wait();
                System.out.println("#### " + Thread.currentThread().getName() + " проснулся!");
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("#### " + Thread.currentThread().getName() + " добавил 1 товар!");
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        System.out.println("#### " + Thread.currentThread().getName() + " будит другой поток!");
        notify();
    }
}
