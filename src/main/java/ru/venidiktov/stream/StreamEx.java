package ru.venidiktov.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API
 * Все методы Stream API не меняют саму коллекцию для которой они были вызваны!
 */
public class StreamEx {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("By");
        list.add("Apple");
        list.add("Orange");
        System.out.println("Список = " + list);

        /**
         * map (Промежуточный) - мапит каждое значение согласно функции в методе map
         * map принимает функциональный интерфейс Function
         */
        // метод stream() выдает поток элементов коллекции
        var lengthList = list.stream().map(String::length).toList();
        System.out.println("Длины слов в списке = " + lengthList);

        //Все методы Stream API не меняют саму коллекцию для которой они были вызваны!
        System.out.println("Сама коллекция не изменилась = " + list);

        Integer[] array = {1, 2, 4, 8, 9};
        System.out.println("Массив = " + Arrays.toString(array));
        var parity = Arrays.stream(array).map(elem -> {
            if (elem % 2 == 0) return "Четное";
            else return "Не четное";
        }).toList();
        System.out.println("Четные и не четные цифры в массиве = " + parity);

        /**
         * filter (Промежуточный) - фильтрует данные в коллекции
         * filter принимает функциональный интерфейс Predicate
         */
        var filterList = list.stream().filter(e -> e.length() > 4).toList();
        System.out.println("Отфильтруем список только тех слов которые длиннее 4 символов = " + filterList);

        /**
         * forEacth (Терминальный) - возвращает void, метод является терминальным
         * forEacth принимает функциональный интерфейс Consumer
         */
        list.forEach(e -> System.out.print("Элемент списка = " + e + " | "));

        /**
         * Создание стрима через фабричный метод of()
         */
        Stream<String> myStream = Stream.of("Sword", "Knife");
        System.out.println();

        /**
         * reduce (Терминальный) - уменьшает N элементов коллекции до 1 с аккумуляцией
         * reduce возвращает Optional
         */
//      reduce(Integer::sum) -> reduce((sum, elem) -> sum = sum + elem)).get();
        var sumLengthWord = list.stream().map(String::length).reduce(Integer::sum).orElse(0);
        System.out.println("Общая длинна слов в списке = " + sumLengthWord);
        //В reduce можно добавить начальное значение в аккумулятор, тут мы не получим Optional
        var sumLengthWord2 = list.stream().map(String::length).reduce(0, Integer::sum);
        System.out.println("Общая длинна слов в списке = " + sumLengthWord2);

        /**
         * sorted (Промежуточный) - сортирует коллекция
         * Есть несколько перегрузок метода sorted, в какой то можно предать Lambda интерфейс Comparator
         */
        //        list.stream().sorted((s1, s2) -> s1.length() - s2.length());
        var sortedList = list.stream().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("Список отсортированный по длинам слов = " + sortedList);

        /**
         * concat (Промежуточный) -объединяет два стрима
         * concat нельзя использовать в цепочке методов
         */
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5, 6);
        System.out.println("Объединение двух стримов через метод concat = " + Stream.concat(stream1, stream2).toList());

        /**
         * distinct (Промежуточный) - оставляет в стриме только уникальные элементы
         * для сравнения используется метод equals!
         */
        var unicList = Stream.of(1, 1, 2, 3, 4, 4, 5, 5).distinct().toList();
        System.out.println("Набор только уникальных элементов = " + unicList);

        /**
         * count (Терминальный) - выдает количество элементов в стриме
         */
        System.out.println("Количество элементов в стриме = " + Stream.of(1, 2, 3, 4, 5).count());

        /**
         * peek (Промежуточный) - принимает Lambda выражение типа Consumer и возвращает в ответ стрим в отличии от forEach
         */
        Stream.of(1, 1, 2, 3, 4, 4, 5, 5).filter(i -> i % 2 == 0)
                .peek(i -> System.out.print("Четное число " + i + " | ")).toList();
        System.out.println();

        /**
         * flatMap (Промежуточный) - используется когда мы работаем с элементами которые находятся внутри элементов стрима
         * Мы достаем подэлементы из элементов стрима и из них делаем один большой стрим
         */
        List<List<String>> transport = new ArrayList<>();
        List<String> carName = new ArrayList<>() {{
            add("Reno");
            add("Nisan");
            add("Mitsubishi");
        }};
        List<String> motoName = new ArrayList<>() {{
            add("Harley-Davidson");
            add("Suzuki");
            add("Honda");
        }};
        transport.add(carName);
        transport.add(motoName);
        transport.stream().flatMap(Collection::stream).forEach(m -> System.out.print("Марка " + m + " | "));
        System.out.println();

        /**
         * findFirst (Терминальный) - выдает первый элемент стрима
         * findFirst возвращает Optional
         */
        var first = Stream.of(99, 1, 1, 2, 3, 4, 4, 5, 5).findFirst().get();
        System.out.println("Первый элемент стрима = " + first);

        /**
         * min max (Терминальные) - Выдают минимальный и максимальный элемент стрима
         * min max принимает Comparator!
         * min max возвращает Optional
         */
        var min = Stream.of(99, 1, 1, 2, 3, 4, 4, 5, 5).min(Integer::compareTo).get();
        var max = Stream.of(99, 1, 1, 2, 3, 4, 4, 5, 5).max(Integer::compareTo).get();
        System.out.println("Минимальный элемент стрима = " + min);
        System.out.println("Максимальный элемент стрима = " + max);

        /**
         * limit (Промежуточный) - ограничивает количество элементов в стриме пропуская последние элементы, возвращает стрим
         */
        var limitList = Stream.of(99, 1, 1, 2, 3, 4, 4, 5, 5).limit(3).toList();
        System.out.println("Первые 3 элемента стрима = " + limitList);

        /**
         * skip (Промежуточный) - ограничивает количество элементов в стриме пропуская первые элементы, возвращает стрим
         */
        var skipList = Stream.of(99, 1, 1, 2, 3, 4, 4, 5, 5).skip(3).toList();
        System.out.println("Пропускаем первые 3 элемента стрима = " + skipList);

        /**
         * mapToInt (Промежуточный) - возвращает стрим элементов int [Есть mapToDouble ... ]
         * Так как метод collect принимает объекты то примитив int нужно преобразовать в его обертку Integer методом boxed!
         * У стрима int есть методы sum, min, max, avg
         */
        var intList = Stream.of("Apple", "Pan", "Cherry").mapToInt(String::length).boxed().collect(Collectors.toList());
        System.out.println("Список из mapToInt = " + intList);
        var sum = Stream.of("Apple", "Pan", "Cherry").mapToInt(String::length).sum();
        System.out.println("Сумма элементов списка из mapToInt = " + sum);

    }
}
