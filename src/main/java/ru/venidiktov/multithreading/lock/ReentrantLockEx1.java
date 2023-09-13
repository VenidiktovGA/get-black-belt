package ru.venidiktov.multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx1 {

    //Lock используется для синхронизации потоков
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        /**
         * Методы можно синхронизировать по одному lock'у
         * Ниже мы создаем потоки которые используют для синхронизации один Lock
         * Когда Lock захвачен ни какие другие потоки не могут выполнять методы где нужно захватить этот Lock!!!
         */

        /**
         * Так как все методы синхронизированы то мы сначала должны закончить один звонок что бы начать следующий
         */
        var thread0 = new Thread(new MyRunnableImplMobile());
        var thread1 = new Thread(new MyRunnableImplSkype());
        var thread2 = new Thread(new MyRunnableImplWhatsapp());
        thread0.start();
        thread1.start();
        thread2.start();
    }

    /**
     * Синхронизированный метод, для синхронизации используется Lock (класс реализующий Lock интерфейс)
     * При использовании Lock нужно всегда освобождать Lock в секции finally!
     */
    static void mobileCall() {
        lock.lock();
        try {
            System.out.println("Mobile call start");
            System.out.println("Замок = " + lock);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Mobile call end");
            lock.unlock();
        }
    }

    /**
     * Синхронизированный метод, для синхронизации используется Lock (класс реализующий Lock интерфейс)
     * При использовании Lock нужно всегда освобождать Lock в секции finally!
     */
    static void whatsappCall() {
        lock.lock();
        try {
            System.out.println("Whatsapp call start");
            System.out.println("Замок = " + lock);
            Thread.sleep(7000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Whatsapp call end");
            lock.unlock();
        }
    }

    /**
     * Синхронизированный метод, для синхронизации используется Lock (класс реализующий Lock интерфейс)
     * При использовании Lock нужно всегда освобождать Lock в секции finally!
     */
    static void skypeCall() {
        lock.lock();
        try {
            System.out.println("Skype call start");
            System.out.println("Замок = " + lock);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Skype call end");
            lock.unlock();
        }
    }

    private static class MyRunnableImplMobile implements Runnable {
        @Override
        public void run() {
            mobileCall();
        }
    }

    private static class MyRunnableImplSkype implements Runnable {
        @Override
        public void run() {
            skypeCall();
        }
    }

    private static class MyRunnableImplWhatsapp implements Runnable {
        @Override
        public void run() {
            whatsappCall();
        }
    }
}
