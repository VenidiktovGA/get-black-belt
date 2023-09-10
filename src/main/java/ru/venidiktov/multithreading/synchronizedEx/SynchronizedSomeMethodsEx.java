package ru.venidiktov.multithreading.synchronizedEx;

/**
 * Synchronized
 * В объекте есть только 1 монитор на весь объект для всех синхронизированных методов в классе!!!
 * Data dace - состояние гонки, это когда несколько потоков конкурируют за один ресурс и изменяют его,
 * изменения ресурса зависит от его предыдущего состояния так же операции над ресурсом которые делают потоки не атомарны,
 * тес самым результат не предсказуем!!!
 * Для решения проблемы гонки можно использовать синхронизированные методы или атомарные переменные
 */
public class SynchronizedSomeMethodsEx {
    //Объект используется как замок, используется монитор объекта для синхронизации методов по нему
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        /**
         * Методы можно синхронизировать по одному монитору
         * Ниже мы создаем потоки которые используют для синхронизации монитор объекта DataRaceSynchronizedMethodEx,
         * тем самым в ответе мы получаем 0
         * Когда монитор объекта DataRaceSynchronizedMethodEx захвачен ни какие другие синхронизованные методы в этом
         * объекте не могут выполняться!!!
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
     * Синхронизированный метод, для синхронизации используется монитор объекта DataRaceSynchronizedMethodEx
     */
    static void mobileCall() {
        synchronized (lock) {
            System.out.println("Mobile call start");
            System.out.println("Монитор = " + lock);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Mobile call end");
        }
    }

    /**
     * Синхронизированный метод, для синхронизации используется монитор объекта DataRaceSynchronizedMethodEx
     */
    static void whatsappCall() {
        synchronized (lock) {
            System.out.println("Whatsapp call start");
            System.out.println("Монитор = " + lock);
            try {
                Thread.sleep(7000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Whatsapp call end");
        }
    }

    /**
     * Синхронизированный метод, для синхронизации используется монитор объекта DataRaceSynchronizedMethodEx
     */
    static void skypeCall() {
        synchronized (lock) {
            System.out.println("Skype call start");
            System.out.println("Монитор = " + lock);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Skype call end");
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
