package ru.venidiktov.multithreading.interruption;

/**
 * Прерываем поток с помощью метода interrupt(), данный метод используется в сочетании с циклом while и методом isInterrupted()
 * что позволяет потоку завершить работу со всеми ресурсами, и ресурсы тем самым будут в оконченном состоянии.
 * Получается, что внешний поток А говорит другому потоку В что он хочет его прервать через метод interrupt()
 * поток B в свою очередь внутри метода run() с помощью цикла while и метода isInterrupted() определяет как реагировать на сигнал от потока А прерваться.
 * (Работа строится на volatile переменной)
 * <p>
 * Метода stop() устарел, он грубо прерывает поток в связи с чем ресурсы с которыми работает поток могли остаться в неизвестном состоянии!
 */
public class InterruptionUserInterruptedMethodsEx {
    public static void main(String[] args) {
        System.out.println(String.format("Основной поток %s запустился", Thread.currentThread().getName()));
        var thread0 = new MyTread();
        thread0.start();

        try {

            Thread.sleep(1000);
            thread0.interrupt(); //Прерываем поток
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println(String.format("Основной поток %s был прерван", Thread.currentThread().getName()));
        }

        System.out.println(String.format("Основной поток %s остановлен", Thread.currentThread().getName()));

    }

    private static class MyTread extends Thread {

        @Override
        public void run() {
            System.out.println(String.format("Поток %s выполняет работу", Thread.currentThread().getName()));
            int count = 1;
            while (!isInterrupted()) {
                try {
                    System.out.println(String.format("Цикл %s", count++));
                    /**
                     * Если поток хотят прервать, а он спит, то что бы не ждать время сна и сразу прервать поток метод sleep()
                     * выкидывает InterruptedException если поток спит, а его хотят прервать через метод interrupt()
                     */
                    Thread.sleep(300); //Генерирует исключение InterruptedException когда мы вызовем метод interrupt() надо потоком
                } catch (InterruptedException e) {
                    /**
                     * Когда поток вызовет метод interrupt, метод sleep сгенерирует исключение InterruptedException,
                     * и управление перейдет к блоку catch. Но если мы проверим статус потока, то увидим, что метод
                     * isInterrupted возвращает false. Как вариант, в этом случае мы можем повторно прервать текущий поток,
                     * опять же вызвав метод interrupt(). Тогда при новой итерации цикла while метода isInterrupted возвратит true,
                     * и произойдет выход из цикла.
                     */
                    interrupt(); // Необходимо для завершения потока, иначе цикл не закончится, можно чтоб не вызывать метод interrupt() обернуть сам while в try catch
//                    break; // Другой вариант чтоб не делать в блоке catch второй вызов interrupt()
                    System.out.println(String.format("Поток %s был прерван", Thread.currentThread().getName()));
                }
            }
            System.out.println(String.format("Поток %s закончил работу", Thread.currentThread().getName()));
        }
    }
}
