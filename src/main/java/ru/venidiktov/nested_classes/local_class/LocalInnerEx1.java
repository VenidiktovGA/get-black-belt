package ru.venidiktov.nested_classes.local_class;

/**
 * Локальный вложенный класс - это класс находящийся внутри блока кода как правило метода
 * Локальный вложенный класс виден только в блоке кода где он объявлен!
 * Локальный вложенный класс не может быть static! (он же временный)
 */
public class LocalInnerEx1 {
    public static void main(String[] args) {
        var myMath = new MyMath();
        // В локальном вложенном класса есть дефолтные значения для делимого и делителя
        myMath.printQuotientRemainder(null, null);

        // В локальном вложенном класса подставятся переданные аргументов метода
        myMath.printQuotientRemainder(44, 3);
    }
}
