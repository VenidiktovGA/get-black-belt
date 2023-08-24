package ru.venidiktov.lambda;

/**
 * Используем функциональный интерфейс как аргумент метода
 */
public class FunctionalInterfaceEx {
    public static void main(String[] args) {
        /**
         * Используем Lambda выражение для представления метода функционального интерфейса
         * Переменные которые являются аргументами Lambda выражения видны во всем Lambda выражении но не за его пределами!
         * Так же в Lambda выражении в его аргументах мы не можем перекрывать внешние параметры
         * В Lambda выражении мы видим внешние переменные, но если они меняются в Lambda выражении их нельзя использовать,
         * получается можно использовать final переменные или effectivly final
         */
        //В Lambda выражении мы не можем перекрыть этот параметр
//        var s = "not right";
        //В Lambda выражении мы видим внешние переменные
        var i = 3; //В Lambda выражении мы видим этот параметр
//        i = 15; //Нельзя менять переменную иначе ее нельзя использовать в Lambda выражении
        def(s -> {
//            String s = "dsf"; //Нельзя создать переменную с таким же именем как и у параметра метода
            int a = 22; //Эту переменную не видно вне Lambda выражения!
            return s.length() + i;
        });

        //s из Lambda выражения не видно за пределами Lambda выражения
//        System.out.println(s);
    }

    static void def(I i) {
        System.out.println(i.abc("Hello world!"));
    }

    @FunctionalInterface
    interface I {
        int abc(String s);
    }
}
