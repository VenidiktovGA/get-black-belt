package ru.venidiktov.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * collect (Терминальный) - используется чтоб преобразовать стрим в коллекцию
 * в методе collect используется Collectors
 */
public class CollectEx {
    public static void main(String[] args) {
        var st1 = new Student("Iosif", 'M', 22, 1);
        var st2 = new Student("Maria", 'F', 22, 2);
        var st3 = new Student("Ivan", 'M', 24, 3);
        var st4 = new Student("Alex", 'M', 23, 2);
        var st5 = new Student("Lena", 'F', 22, 2);
        var students = new ArrayList<Student>() {{
            add(st1);
            add(st2);
            add(st3);
            add(st4);
            add(st5);
        }};

        /**
         * Collectors.groupingBy группировка элементов стрима по какому то признаку
         * Сгруппируем студентов по методу в стриме
         */
        Map<Character, List<Student>> studentsGroupBySex = students.stream().map(st -> {
            st.setName(st.getName().toUpperCase());
            return st;
        }).collect(Collectors.groupingBy(Student::getSex));
        System.out.println("Студенты отсортированные по полу!");
        studentsGroupBySex.entrySet().forEach(gr -> System.out.println(gr.getKey() + ": " + gr.getValue()));

        /**
         * Collectors.partitioningBy делит элементы стрима на две группы по условию Lambda выражения Predicate
         * Группировка студентов кто на первом курсе и всех остальных
         */
        Map<Boolean, List<Student>> studentsPartitionByCourse = students.stream()
                .collect(Collectors.partitioningBy(st -> st.getCourse() == 1));
        System.out.println("Две группы студентов кто на 1 курсе и всех остальных!");
        studentsPartitionByCourse.entrySet().forEach(pr -> System.out.println(pr.getKey() + ": " + pr.getValue()));
    }

    private static class Student {
        String name;
        char sex;
        int age;
        int course;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    ", course=" + course +
                    '}';
        }

        public Student(String name, char sex, int age, int course) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public char getSex() {
            return sex;
        }

        public void setSex(char sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
        }
    }
}
