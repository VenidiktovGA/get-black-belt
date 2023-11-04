package ru.venidiktov.regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * У класса String есть методы которые работаю с регулярными выражениями
 * <p>
 * matchs() - проверяет есть ли совпадения по регулярному выражению в строке, возвращает boolean
 * splet() - делит строку на подстроки согласно регулярному выражению, возвращает String[]
 */
public class StringMatchesAndSplitEx {
    public static void main(String[] args) {
        var s = "Ivanov Vasiliy, Russia, Moscow, Lenin street, 51, Flat 48," +
                " email: vivanov@mail.ru, Postalcode: AA99, Phone Number: +123456789;" +
                "Petrova Mariya, Ukraine, Kiyev, Lomonosov street, 33, Flat 18," +
                " email: masha@yandex.ry, Postcode: UKR54, Phone Number: +987654321;" +
                "Chuck Norris, USA, Hollywood, All stars street, 87, Flat 21," +
                " email: chuck@gmail.com, Postalcode: USA23, Phone Number: +136478952.";

        String s2 = "chuck@gmail.com";
        Pattern emailPattern = Pattern.compile("\\w+@\\w+\\.\\w+");
        boolean isEmail = s2.matches(emailPattern.pattern());
        if (isEmail) {
            System.out.println(String.format("Строка {%s} соответствует паттерну %s", s2, emailPattern));
        }
        System.out.println();

        String[] array = s.split(" ");
        System.out.println("Массив слов со знаками припенания строки разделенных символом пробел \" \"");
        System.out.println(Arrays.toString(array));
    }
}
