package ru.venidiktov.generics;

/**
 * Generics позволяют параметризировать классы, это позволяет переиспользовать код
 */
public class ParameterizedClass {
    public static void main(String[] args) {
        Info<String> info1 = new Info<>("Hello!!");
        System.out.println(info1);

        PrivateInfo privateInfo = new PrivateInfo("I'm your father");
        Info<PrivateInfo> info2 = new Info<>(privateInfo);
        System.out.println(info2);
    }


    static class Info <T> { // T - это type place holder (заполнитель типа)
        private T value; //Параметризированное поле не может быть static, так как оно должно было бы быть одинаково у всего класса

        public Info(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{[" + value + "]}"; //У value неявно вызывается toString()
        }
    }

    static class PrivateInfo {
        private String privateInfo;

        public PrivateInfo(String privateInfo) {
            this.privateInfo = privateInfo;
        }

        @Override
        public String toString() {
            return "This is private information! " + privateInfo;
        }
    }
}
