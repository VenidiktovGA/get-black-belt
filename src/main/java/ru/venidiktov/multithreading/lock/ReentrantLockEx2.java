package ru.venidiktov.multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx2 {
    public static void main(String[] args) {
        /**
         * Тут потоки последовательно попробуют захватить lock,
         * самый первый из потоков кто попытался захватить lock захватит,
         * остальные потоки будут в очереди и будут захватывать lock после его освобождения в порядке очереди
         */
        final Lock lock = new ReentrantLock();
        new Employee("Iosif", lock);
        new Employee("Maria", lock);
        new BusyEmployee("Mark", lock);
        new Employee("Alex", lock);
        new Employee("Janna", lock);
    }

    private static class Employee extends Thread {
        private String name;
        private Lock lock;

        @Override
        public void run() {
            System.out.println(String.format("Работник %s ждет у банкомата", name));
            lock.lock();
            try {
                System.out.println(String.format("Работник %s пользуется банкоматом", name));
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(String.format("Работник %s освобождает банкомат", name));
                lock.unlock();
            }
        }

        public Employee(String name, Lock lock) {
            this.name = name;
            this.lock = lock;
            this.start(); // Сразу запускаем поток при создании экземпляра класса
        }
    }

    private static class BusyEmployee extends Thread {
        private String name;
        private Lock lock;

        @Override
        public void run() {
            System.out.println(String.format("Занятый Работник %s ждет у банкомата", name));
            if (lock.tryLock()) {
                try {
                    System.out.println(String.format("Занятый Работник %s пользуется банкоматом", name));
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("Занятый Работник %s освобождает банкомат", name));
                    lock.unlock();
                }
            }
            System.out.println(String.format("Занятый Работник %s не хочет стоять в очереди, он уходит делать свои дела", name));
        }

        public BusyEmployee(String name, Lock lock) {
            this.name = name;
            this.lock = lock;
            this.start(); // Сразу запускаем поток при создании экземпляра класса
        }
    }
}
