package ru.venidiktov.multithreading;

/**
 * Жизненный цикл потока
 * • New - когда поток только создан (new Thread()), до вызова метода start(
 * • Runnable - когда у потока вызывается метод start(), в этом состоянии есть два под состояния Ready, Running
 * • Ready - когда поток готов к выполнению, возможно ждет в очереди поток когда его запустят
 * • Running - когда поток на самом деле выполняется
 * • Terminated - когда работа потока завершена
 */
public class ThreadLifeCycleEx {
    public static void main(String[] args) throws InterruptedException {

        /**
         * !!!! ВАЖНО !!!!
         * Если поток работает очень быстро без задержек, то пока выводится информация о его состоянии а это медленно
         * состояние потока может изменится или поток вообще закончит выполнение!
         */

        //New
        var thread0 = new Worker();
        System.out.println(String.format("Поток %s в состоянии = %s", thread0.getName(), thread0.getState()));

        //Runnable
        thread0.start();
        System.out.println(String.format("Поток %s в состоянии = %s", thread0.getName(), thread0.getState()));

        //Terminated
        thread0.join();
        System.out.println(String.format("Поток %s в состоянии = %s", thread0.getName(), thread0.getState()));
    }

    private static class Worker extends Thread {
        @Override
        public void run() {
            //Running
            System.out.println(String.format("Поток %s в состоянии = %s", this.getName(), this.getState()));
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
