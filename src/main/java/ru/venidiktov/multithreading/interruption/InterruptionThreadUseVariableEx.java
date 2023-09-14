package ru.venidiktov.multithreading.interruption;

/**
 * Прерываем поток по средствам изменения переменной отвечающей за выполнение бесконечного цикла в экземпляре класса нашего потока
 * По идее потоки лучше завершать корректно что бы используемые ими переменные не остались в неизвестном состоянии
 */
public class InterruptionThreadUseVariableEx {
    public static void main(String[] args) {
        System.out.println(String.format("Основной поток %s запустился", Thread.currentThread().getName()));
        var myRunnable = new MyRunnable();
        var thread0 = new Thread(myRunnable);
        thread0.start();

        try {

            Thread.sleep(1000);
            myRunnable.disable(); //Меняем переменную в экземпляре
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println(String.format("Основной поток %s был прерван", Thread.currentThread().getName()));
        }

        System.out.println(String.format("Основной поток %s остановлен", Thread.currentThread().getName()));

    }

    private static class MyRunnable implements Runnable {
        private Boolean isActive;

        public MyRunnable() {
            isActive = true;
        }

        public void disable() {
            isActive = false;
        }

        @Override
        public void run() {
            System.out.println(String.format("Поток %s выполняет работу", Thread.currentThread().getName()));
            int count = 1;
            while (isActive) {
                try {
                    System.out.println(String.format("Цикл %s", count++));
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(String.format("Поток %s был прерван", Thread.currentThread().getName()));
                }
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
