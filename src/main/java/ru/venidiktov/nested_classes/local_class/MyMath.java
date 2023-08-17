package ru.venidiktov.nested_classes.local_class;

import java.time.LocalDate;

public class MyMath {
    private static final String MADE_BY = "Venidiktov G.A";

    // Результат частное и остаток от деления
    public void printQuotientRemainder(Integer inDividend, Integer inDivisor) {
        final LocalDate TODAY = LocalDate.now();
        class Division {
            private int dividend; //делимое
            private int divisor; // делитель
            private static final int DEFAULT_DIVIDEND = 21;
            private static final int DEFAULT_DIVISOR = 4;

            // частное
            public int getQuotient() {
                return dividend / divisor;
            }

            // остаток
            public int getRemainder() {
                return dividend % divisor;
            }

            public int getDividend() {
                return dividend;
            }

            public void setDividend(int dividend) {
                this.dividend = dividend;
            }

            public int getDivisor() {
                return divisor;
            }

            public void setDivisor(int divisor) {
                this.divisor = divisor;
            }

            public String getPrivateFieldOuterClass() {
                return "Этот класс производства = " + MADE_BY;
            }

            public String getFinalBlockVariable() {
                return "Сегодня = " + TODAY;
            }
        }
        var division = new Division();
        division.setDividend(inDividend != null ? inDividend : Division.DEFAULT_DIVIDEND);
        division.setDivisor(inDivisor != null ? inDivisor : Division.DEFAULT_DIVISOR);
        System.out.println("Делимое = " + division.getDividend());
        System.out.println("Делитель = " + division.getDivisor());
        System.out.println("Частное = " + division.getQuotient());
        System.out.println("Остаток от деления = " + division.getRemainder());
        System.out.println("Локальный вложенный класс имеет доступ к private полям внешнего класса! " + division.getPrivateFieldOuterClass());
        System.out.println("Локальный вложенный класс имеет доступ к final или effectively final переменным блока кода куда он вложен! " + division.getFinalBlockVariable());
    }
}
