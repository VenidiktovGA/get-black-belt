package ru.venidiktov.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Используя регулярные выражения преобразуем формат карт из сплошных цифр в удобочитаемый,
 * в такой 03/25 1234 5678 9123 4567 (987)
 */
public class FormatCardInfoEx {
    public static void main(String[] args) {
        var s = "12345678912345679870325;"
                + "12333678912345672270125;"
                + "62333678912335672270323;";
        System.out.println("Исходная строка: " + s);
        System.out.println();

        Pattern cardInfoPattern = Pattern.compile("(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{2})(\\d{2})(\\d{3})");
        Matcher cardInfoMatcher = cardInfoPattern.matcher(s);
        String formatCardInfo = cardInfoMatcher.replaceAll("$5/$6 $1 $2 $3 $4 ($7)");
        String[] formatCardsInfo = formatCardInfo.split(";");
        System.out.println("Отформатированная информация о картах:");
        Arrays.stream(formatCardsInfo).forEach(System.out::println);
    }
}
