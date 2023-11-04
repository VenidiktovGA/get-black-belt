package ru.venidiktov.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEx1 {
    public static void main(String[] args) {
        var s = "Ivanov Vasiliy, Russia, Moscow, Lenin street, 51, Flat 48," +
                " email: vivanov@mail.ru, Postalcode: AA99, Phone Number: +123456789;" +
                "Petrova Mariya, Ukraine, Kiyev, Lomonosov street, 33, Flat 18," +
                " email: masha@yandex.ry, Postcode: UKR54, Phone Number: +987654321;" +
                "Chuck Norris, USA, Hollywood, All stars street, 87, Flat 21," +
                " email: chuck@gmail.com, Postalcode: USA23, Phone Number: +136478952.";

        /**
         * Найдет все слова из букв и цифр и не включит в них другие символы кроме символа _
         * по сути найдет все слова
         */
        Pattern pattern = Pattern.compile("\\w+");
        int countMatch = 0;
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            countMatch++;
        }
        System.out.println(String.format("По шаблону %s найдено %s совпадений", pattern, countMatch));
        System.out.println();

        /**
         * Найдем номера домов и квартир
         */
        Pattern homeNumberPattern = Pattern.compile("\\b\\d{2}\\b");
        Matcher homeNubmerMatcher = homeNumberPattern.matcher(s);
        while (homeNubmerMatcher.find()) {
            System.out.println("Найден номер дома или квартиры = " + homeNubmerMatcher.group());
        }
        System.out.println();

        /**
         * Найдем номера телефонов
         */
        Pattern phoneNumberPattern = Pattern.compile("\\+\\d{9}\\b");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(s);
        while (phoneNumberMatcher.find()) {
            System.out.println("Найден номер телефона = " + phoneNumberMatcher.group());
        }
        System.out.println();

        /**
         * Найдем email'ы
         */
        Pattern emailPattern = Pattern.compile("\\w+@\\w+\\.\\w+");
        Matcher emailMatcher = emailPattern.matcher(s);
        while (emailMatcher.find()) {
            System.out.println("Найден email = " + emailMatcher.group());
        }
        System.out.println();

    }
}
