package ru.venidiktov.multithreading;

/**
 * Ключевое слово volatile говорит потоку что, значение данной переменной не нужно копировать в память потока (свою кэш память),
 * значение данной переменной нужно брать из main memory (общей памяти).
 */
public class VolatileEx extends Thread {
    /**
     * Если переменная flag не будет volatile то поток по ссылке в переменной thread не увидит ее изменения и не остановит цикл while!
     */
    volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        var thread = new VolatileEx();
        thread.start();
        Thread.sleep(3000);
        System.out.println("Прошло 3 секунды после старта!");
        System.out.println("Хотим остановить цикл");
        flag = false; // Изменяем volatile переменную
        thread.join();
        System.out.println("Конец программы");
    }

    @Override
    public void run() {
        long counter = 0;
        while (flag) {
            counter++;
        }
        System.out.println("Цикл завершен counter = " + counter);
    }
}
