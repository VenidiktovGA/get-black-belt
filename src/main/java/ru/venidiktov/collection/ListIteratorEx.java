package ru.venidiktov.collection;

import java.util.ArrayList;
import java.util.List;

/***
 * Проверяем строку на палиндром
 */
public class ListIteratorEx {
    public static void main(String[] args) {
        var inString = "топот";
        List<Character> listChar = new ArrayList<>();
        for(Character ch: inString.toCharArray()) {
            listChar.add(ch);
        }

        System.out.printf(
                "Слово %s это палиндром ? Ответ: %s%n",
                inString,
                ListIteratorEx.isPalindrome(listChar)
        );
    }

    public static boolean isPalindrome(List<Character> word) {
        var iterator = word.listIterator();
        var reverseIterator = word.listIterator(word.size());

        while(iterator.hasNext() && reverseIterator.hasPrevious()) {
            var left = iterator.next();
            var right = reverseIterator.previous();
            System.out.println(String.format("Слева '%s' == '%s' Справа, ответ: %s", left, right, (left.equals(right))));
            if(!left.equals(right)) return false;
        }
        return true;
    }
}
