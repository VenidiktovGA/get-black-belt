package ru.venidiktov.generics;

/**
 * Класс может быть параметризирован множеством заполнителей, в данном примере их два V1, V2
 */
public class ParametrizedClassWith2 {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Hello", 25);
        System.out.println("Pair: value1 = " + pair.getValue1() + ", value2 = " + pair.getValue2());
    }

    static class Pair<V1, V2> {
        private V1 value1;
        private V2 value2;

        public Pair(V1 value1, V2 value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public V1 getValue1() {
            return value1;
        }

        public V2 getValue2() {
            return value2;
        }
    }
}
