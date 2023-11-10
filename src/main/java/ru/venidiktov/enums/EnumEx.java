package ru.venidiktov.enums;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Enum - представляет из себя набор константных значение, обычно значения в enum имеют общую природу,
 * enum обычно используется для ограничения возможных значений
 * <p>
 * Конструктор в enum всегда private
 * От enum нельзя наследоваться
 * Enum Это синглтон
 */
public class EnumEx {
    public static void main(String[] args) {
        int numberDayOfWeek = LocalDate.now().getDayOfWeek().getValue(); // Тут конечно можно сразу день недели текстом вывести, кстати там то же enum
        DayOfWeek dayOfWeek = DayOfWeek.values()[numberDayOfWeek - 1];
        System.out.println(String.format("Сегодня %s (%s)", dayOfWeek, dayOfWeek.getRuNameDayOfWeek()));
        System.out.println();

        /**
         * Так как enum синглтон то при сравнении одинаковых типов Enum они будут равны как через == так и через equals()
         */
        System.out.println("enum сравниваются как строки, и так как у нас есть пул строк один и тот же элемент enum указывает на одну и ту же строку");
        System.out.println(String.format("DayOfWeek.MONDAY == DayOfWeek.MONDAY = %s", DayOfWeek.MONDAY == DayOfWeek.MONDAY));
        System.out.println(String.format("DayOfWeek.MONDAY == DayOfWeek.TUESDAY = %s", DayOfWeek.MONDAY == DayOfWeek.TUESDAY));
//        System.out.println(String.format("DayOfWeek.MONDAY == DayOfWeek2.MONDAY = %s", DayOfWeek.MONDAY == DayOfWeek2.MONDAY)); // Так нельзя сравнивать так как они разного типа
//        System.out.println(String.format("DayOfWeek.MONDAY == DayOfWeek2.TUESDAY = %s", DayOfWeek.MONDAY == DayOfWeek2.TUESDAY)); // Так ньзя сравнивать так как они разного типа
        /**
         * Сравнение вернет false так как при сравнении через equals сравниваются объекты а ни будут разные хоть и оба синглтоны
         */
        System.out.println(String.format("DayOfWeek.MONDAY.equals(DayOfWeek2.MONDAY) = %s", DayOfWeek.MONDAY.equals(DayOfWeek2.MONDAY)));
        System.out.println(String.format("DayOfWeek.MONDAY.equals(DayOfWeek2.TUESDAY) = %s", DayOfWeek.MONDAY.equals(DayOfWeek2.TUESDAY)));
        System.out.println();

        /**
         * Метод valueOf() выдает элемент enum из строки
         */
        System.out.println(String.format("Метод valueOf() выдает элемент enum из строки DayOfWeek.valueOf(\"WEDNESDAY\") = %s", DayOfWeek.valueOf("WEDNESDAY")));
        System.out.println();

        /**
         * Метод values() выдает массив элементов enum
         */
        DayOfWeek[] weekDays = DayOfWeek.values();
        System.out.println("Метод values() выдает массив элементов enum: " + Arrays.toString(weekDays));
    }

    private static enum DayOfWeek {
        MONDAY("Понедельник"),
        TUESDAY("Вторник"),
        WEDNESDAY("Среда"),
        THURSDAY("Четверг"),
        FRIDAY("Пятница"),
        SATURDAY("Суббота"),
        SUNDAY("Воскресенье");

        private String ruNameDayOfWeek;

        DayOfWeek(String ruNameDayOfWeek) {
            this.ruNameDayOfWeek = ruNameDayOfWeek;
        }

        public String getRuNameDayOfWeek() {
            return ruNameDayOfWeek;
        }
    }

    private static enum DayOfWeek2 {
        MONDAY("Понедельник"),
        TUESDAY("Вторник"),
        WEDNESDAY("Среда"),
        THURSDAY("Четверг"),
        FRIDAY("Пятница"),
        SATURDAY("Суббота"),
        SUNDAY("Воскресенье");

        private String ruNameDayOfWeek;

        DayOfWeek2(String ruNameDayOfWeek) {
            this.ruNameDayOfWeek = ruNameDayOfWeek;
        }

        public String getRuNameDayOfWeek() {
            return ruNameDayOfWeek;
        }
    }
}
