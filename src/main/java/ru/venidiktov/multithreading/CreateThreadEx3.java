package ru.venidiktov.multithreading;

/**
 * В java для создания потока есть разные способы
 * 3
 * Можно по быстрому создать поток если предать туда анонимный класс или Lambda интерфейс
 */
public class CreateThreadEx3 {
    public static void main(String[] args) {

        /**
         * Использовать анонимный класс
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Привет многопоточность 2!");
            }
        }).start();


        /**
         * Можно использовать Lambda выражение
         */
        new Thread(() -> System.out.println("Привет многопоточность!")).start();
    }
}
