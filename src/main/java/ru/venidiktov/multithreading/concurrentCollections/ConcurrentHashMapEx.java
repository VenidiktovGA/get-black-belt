package ru.venidiktov.multithreading.concurrentCollections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap реализует параллельную работу нескольких потоков одновременно за счет
 * разделения на сегменты, получается что ConcurrentHashMap предоставляет одновременный доступ потоку только в 1 сегмент
 * соответственно сколько сегментов в ConcurrentHashMap столько потоков и могут с ней работать одновременно.
 * Чтение из ConcurrentHashMap идет без блокировки
 */
public class ConcurrentHashMapEx {
    public static void main(String[] args) throws InterruptedException {

        /**
         * При использовании не синхронизованной HashMap мы получим ConcurrentModificationException
         */
//        Map<Integer, String> map = new HashMap<>() {{
//            put(8, "Bruant");
//            put(22, "Karim");
//        }};
        /**
         * ConcurrentHashMap позволяет работать с коллекцией нескольким потокам одновременно
         */
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>() {{
            put(8, "Bruant");
            put(22, "Karim");
            put(3, "Gennady");
            put(21, "Rouz");
            put(2, "Shakil");
//            put(null, "dfdf"); // В ConcurrentHashMap ключи не могут быть NULL, получим NullPointerException
//            put(7, null); // В ConcurrentHashMap значения не могут быть NULL, получим NullPointerException
        }};

        Runnable runnable1 = () -> {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Integer key = iterator.next();
                System.out.println(String.format("%s: %s", key, map.get(key)));
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(23, "Jordan");
        };


        var thread0 = new Thread(runnable1);
        var thread1 = new Thread(runnable2);
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
    }
}

