package ru.venidiktov.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern - класс представляющий собой регулярное выражение, оболочка регулярного выражения в виде строки
 * Matcher - класс содержащий результат сравнения регулярного выражения с входной строкой, метод matcher()
 * <p>
 * Используя Pattern и Matcher вместо методов строки которые принимают регулярные выражения мы выигрываем в производительности!
 * <p>
 * В регулярном выражении используется двойной бэк слэш \\ для спецсимволов, почему двойной, потому что одинарный бэк слэш занят для экранирования кавычек в строке \" !!!
 */
public class RegexIntroductionEx {
    public static void main(String[] args) {
        var s1 = "ABCD ABCE ABCFABCGABCH";

        System.out.println("ABC - простая строка ABC");
        Pattern pattern1 = Pattern.compile("ABC"); // Создали регулярное выражение
        Matcher matcher1 = pattern1.matcher(s1); // Поискали совпадения в тексте согласно регулярному выражению

        while (matcher1.find()) { // Возвращает true столько раз сколько раз было найдено совпадений регулярному выражению в тексте
            System.out.println(String.format("Совпадение {%s} найдено на позиции {%s}", matcher1.group(), matcher1.start())); // Выводим совпадение
        }
        System.out.println();

        /**
         * [abc] - любой символ из скобок
         */
        System.out.println("[abc] - любой символ из скобок");
        Pattern pattern2 = Pattern.compile("[ABC]");
        Matcher matcher2 = pattern2.matcher(s1);
        var countMatchMatcher2 = 0;
        while (matcher2.find()) {
            countMatchMatcher2++;
        }
        System.out.println(String.format("Количество совпадений с {%s} в строке {%s} = %s", pattern2, s1, countMatchMatcher2));
        System.out.println();

        /**
         * [a-f] - соответствует любой букве от a до f
         */
        System.out.println("[a-f] - соответствует любой букве от a до f");
        Pattern pattern3 = Pattern.compile("AB[A-Z]E");
        Matcher matcher3 = pattern3.matcher(s1);
        while (matcher3.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher3.group(), s1, pattern3, matcher3.start()));
        }
        System.out.println();

        /**
         * [^a-f] - символ ^ в начале квадратных скобок означает отрицание, то есть символы из скобок не подходят
         */
        System.out.println("[^a-f] - символ ^ в начале квадратных скобок означает отрицание, то есть символы из скобок не подходят");
        Pattern pattern4 = Pattern.compile("ABC[^D-F]");
        Matcher matcher4 = pattern4.matcher(s1);
        while (matcher4.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher4.group(), s1, pattern4, matcher4.start()));
        }
        System.out.println();

        /**
         * [a|f] - символ | означает логическое или
         */
        System.out.println("[a|f] - символ | означает логическое или");
        Pattern pattern5 = Pattern.compile("ABC[D|F]");
        Matcher matcher5 = pattern5.matcher(s1);
        while (matcher5.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher5.group(), s1, pattern5, matcher5.start()));
        }
        System.out.println();

        /**
         * . - символ . означает любой символ, кроме символа новой строки
         */
        System.out.println(". - символ . означает любой символ, кроме символа новой строки");
        Pattern pattern6 = Pattern.compile("ABC.");
        Matcher matcher6 = pattern6.matcher(s1);
        while (matcher6.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher6.group(), s1, pattern6, matcher6.start()));
        }
        System.out.println();

        /**
         * ^ - символ ^ означает начало строки
         * $ - символ $ означает конец строки
         */
        System.out.println("^ - символ ^ означает начало строки\n$ - символ $ означает конец строки");
        var s7 = "ABCD";
        Pattern pattern7 = Pattern.compile("^ABC.$");
        Matcher matcher7 = pattern7.matcher(s7);
        while (matcher7.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher7.group(), s7, pattern7, matcher7.start()));
        }
        System.out.println();

        /**
         * \\d - мета символ \\d означает цифру
         * \\D - мета символ \\D означает не цифру
         */
        System.out.println("\\\\d - мета символ \\\\d означает цифру\n\\\\D - мета символ \\\\D означает не цифру");
        var s8 = "A77A";
        Pattern pattern8 = Pattern.compile("\\D\\d");
        Matcher matcher8 = pattern8.matcher(s8);
        while (matcher8.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher8.group(), s8, pattern8, matcher8.start()));
        }
        System.out.println();

        /**
         * \\w - мета символ \\w означает букву цифру или символ _
         * \\W - мета символ \\W означает символ не букву не цифру и не символ _
         */
        System.out.println("\\\\w - мета символ \\\\w означает букву цифру или символ _\n\\\\W - мета символ \\\\W означает символ не букву не цифру и не символ _");
        var s9 = "A7\0_A\0";
        Pattern pattern9 = Pattern.compile("\\w\\W");
        Matcher matcher9 = pattern9.matcher(s9);
        while (matcher9.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher9.group(), s9, pattern9, matcher9.start()));
        }
        System.out.println();

        /**
         * ? - символ ? означает 0 или 1 повторение
         */
        System.out.println("? - символ ? означает 0 или 1 повторение");
        var s10 = "12";
        Pattern pattern10 = Pattern.compile("\\d?");
        Matcher matcher10 = pattern10.matcher(s10);
        while (matcher10.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher10.group(), s10, pattern10, matcher10.start()));
        }
        System.out.println();

        /**
         * * - символ * означает 0 или бесконечное количество повторение
         */
        System.out.println("* - символ * означает 0 или бесконечное количество  повторение");
        var s11 = "12";
        Pattern pattern11 = Pattern.compile("\\d*");
        Matcher matcher11 = pattern11.matcher(s11);
        while (matcher11.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher11.group(), s11, pattern11, matcher11.start()));
        }
        System.out.println();

        /**
         * + - символ + означает 1 или бесконечное количество повторение
         */
        System.out.println("+ - символ + означает 1 или бесконечное количество повторение");
        var s12 = "12";
        Pattern pattern12 = Pattern.compile("\\d+");
        Matcher matcher12 = pattern12.matcher(s12);
        while (matcher12.find()) {
            System.out.println(String.format("Совпадение {%s} в строке {%s} по паттерну {%s} найдено на позиции %s", matcher12.group(), s12, pattern12, matcher12.start()));
        }
        System.out.println();

        /**
         * \s - мета символ \s означает пробельный символ
         * Пробельный символ соответствует управляющим символам [\t\n\r\f\v]
         */
        System.out.println("\\s - мета символ \\s означает пробельный символ");
        var s13 = "Hello   java world";
        Pattern pattern13 = Pattern.compile("\\b\\s{2,}\\b");
        Matcher matcher13 = pattern13.matcher(s13);
        while (matcher13.find()) {
            System.out.println("В тексте между словами есть лишние пробелы!");
        }
        System.out.println();

        /**
         * \S - мета символ \S означает не пробельный символ
         * Не пробельный символ соответствует символам кроме [\t\n\r\f\v], любая цифра любая буква!
         */
        System.out.println("\\S - мета символ \\S означает не пробельный символ");
        var s14 = "Hello,, java world";
        Pattern pattern14 = Pattern.compile("\\b\\S+\\b");
        Matcher matcher14 = pattern14.matcher(s14);
        while (matcher14.find()) {
            System.out.println(String.format("По шаблону %s из строки %s найдено совпадение %s", pattern14, s14, matcher14.group()));
        }
        System.out.println();

        /**
         * () - обозначает скобочную группу, к группе можно обращаться
         */
        System.out.println("() - обозначает скобочную группу, к группе можно обращаться");
        var s15 = "ABCDABABDACDABABAB";
        Pattern pattern15 = Pattern.compile("D(AB){2,3}");
        Matcher matcher15 = pattern15.matcher(s15);
        while (matcher15.find()) {
            System.out.println(String.format("По шаблону %s из строки %s найдено совпадение %s", pattern15, s15, matcher15.group()));
        }
        System.out.println();

        /**
         * \A - мета символ \A обозначает выражение в начале строки
         */
        System.out.println("\\A - мета символ \\A обозначает выражение в начале строки");
        var s16 = "java world";
        Pattern pattern16 = Pattern.compile("\\Aj");
        Matcher matcher16 = pattern16.matcher(s16);
        while (matcher16.find()) {
            System.out.println("Строка начинается с символа j");
        }
        System.out.println();

        /**
         * \Z - мета символ \Z обозначает выражение в конце строки
         */
        System.out.println("\\Z - мета символ \\Z обозначает выражение в конце строки");
        var s17 = "java world";
        Pattern pattern17 = Pattern.compile("\\Zd");
        Matcher matcher17 = pattern16.matcher(s17);
        while (matcher17.find()) {
            System.out.println("Строка заканчивается символом d");
        }
        System.out.println();
    }
}