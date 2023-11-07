package ru.venidiktov.regex;

/**
 * Для замены в классе String есть методы позволяющие заменить необходимый кусочек на то что нам надо, в качестве заменяемого текста можно указывать regEx
 * <p>
 * метод replaceAll() - принимает в качестве заменяемого текста regEx
 * метод replace() - заменит все подстроки на указанную строку
 */
public class StringReplaceEx {
    public static void main(String[] args) {
        System.out.println("Заменим множественные пробелы одним!");
        var s1 = "Hello      my   friend, how your Java?";
        System.out.println("Исходная строка =====: " + s1);
        System.out.println("Исправленная строка =: " + s1.replaceAll("\\b +\\b", " ")); // Можно такую регулярку использовать " {2,}"

        System.out.println("Заменим все подстроки \"java\" на \"linux\"!");
        var s2 = "I'm learn java because i'm java programmer!";
        System.out.println("Исходная строка =====: " + s2);
        System.out.println("Исправленная строка =: " + s2.replace("java", "linux")); // Тут указывается именно строка
    }
}
