package ru.venidiktov.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка IP адреса
 * <p>
 * Считаем IP адрес достоверным если он соответствует виду: 0-255.0-255.0-255.0-255
 */
public class RegExIPCheckEx {
    public static void main(String[] args) {
        /**
         * ^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)(\.(?!$)|$)){4}$
         * ^(){4}$ - скобочная группа идет с начала строки и до ее конца, скобочная группа повторяется 4 раза
         * (25[0-5]|(2[0-4]|1\d|[1-9]|)\d) - скобочная группа внутри нее может быть 250-555 или скобочная группа (2[0-4]|1\d|[1-9]|) и любая цифра
         * (2[0-4]|1\d|[1-9]|) - скобочная группа внутри нее может быть 20-24 или 10-19 или 1-9 или ничего судя по последнему символу | (сопоставление нулевой длинны)
         * (\.(?!$)|$)) - скобочная группа внутри нее может быть символ точка '.' после данного символа не должно быть конца строки символ $ (Негативный просмотр вперед) или будет конец строки $
         */
        Pattern IPPattern = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$");

        String someIPAddress = "192.17.0.255";
        Matcher matcher = IPPattern.matcher(someIPAddress);
        System.out.println(String.format("Это %s IP адрес = %s", someIPAddress, matcher.matches()));

        String someIPAddress2 = "192.17.0.256";
        Matcher matcher2 = IPPattern.matcher(someIPAddress2);
        System.out.println(String.format("Это %s IP адрес = %s", someIPAddress2, matcher2.matches()));
    }
}
