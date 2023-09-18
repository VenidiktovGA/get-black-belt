package ru.venidiktov.multithreading.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore это более высокоуровневая реализация блокировки доступа к ресурсу,
 * с возможностью дать доступ одновременно указанному количеству потоков
 * <p>
 * Допустим у нас есть 2 телефонные будки, и 5 людей которые хотят ими воспользоваться
 */
public class SemaphoreEx {
    public static void main(String[] args) {
        Semaphore callBox = new Semaphore(2); // Одновременно доступ к ресурсу могут получить 2 потока
        new Person("Ivan", callBox);
        new Person("Maria", callBox);
        new Person("Iosif", callBox);
        new Person("Neo", callBox);
        new Person("Gandalf", callBox);

    }

    private static class Person extends Thread {
        private String name;
        private Semaphore callBox;

        public Person(String name, Semaphore callBox) {
            this.name = name;
            this.callBox = callBox;
            this.start();
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("%s ждет", name));
                /**
                 * acquire() обращается к семафору чтоб получить ресурс, если ресурс не выдадут поток будет заблокирован
                 * до момента пока ресурс не освободится
                 */
                callBox.acquire();
                System.out.println(String.format("%s пользуется телефоном", name));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                /**
                 * ! Обязательно в finally нужно освободить ресурс, чтобы счетчик потоков которым можно выдать ресурс в Semaphore увеличился
                 */
                System.out.println(String.format("%s закончил пользоваться телефоном", name));
                callBox.release();
            }
        }
    }
}
