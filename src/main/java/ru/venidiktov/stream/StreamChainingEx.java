package ru.venidiktov.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Методы Stream API можно вызывать в цепочке то есть друг за другом - Method Chaining
 */
public class StreamChainingEx {
    public static void main(String[] args) {
        List<Company> list = new ArrayList<>();
        list.add(new Company("Google", "Software", 24));
        list.add(new Company("Amazon", "Software", 29));
        list.add(new Company("Apple", "Software and Hardware", 47));
        list.add(new Company("Facebook", "Software", 19));
        list.add(new Company("Intel", "Hardware", 55));
        list.add(new Company("AMD", "Hardware", 54));
        System.out.println("Список = " + list);

        var res = list.stream()
                .filter(comp -> comp.type.contains("Software"))
                .sorted(Comparator.comparingInt(Company::getAge))
                .map(comp -> comp.name).toList();
        System.out.println("Имена компаний которые Software в порядке возрастания лет существования = " + res);

        var sumAge = list.stream().mapToInt(Company::getAge).sum();
        System.out.println("Сумма лет всех компаний = " + sumAge);

        /**
         * !! Промежуточные методы вызовутся только тогда когда будет вызван терминальный метод !!
         * промежуточные методы lazy они ждут вызова терминального метода
         * В примере ниже мы не увидим на консоли слова 'Фильтруем элемент n' до тех пор пока мы не напишем терминальный метод!
         */
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.filter(el -> {
            System.out.println("Фильтруем элемент " + el);
            return el % 2 == 0;
        });

    }

    public static class Company {
        private String name;
        private String type;
        private int age;

        public Company(String name, String type, int age) {
            this.name = name;
            this.type = type;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
