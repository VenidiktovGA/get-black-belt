package ru.venidiktov.collection.queue;

import java.util.PriorityQueue;

/***
 * В PriorityQueue в отличии от обычной Queue элементы извлекаются и хранятся в очереди согласно приоритету,
 * приоритет реализуется имплементрацией интерфейса Comparable, или передачей Comparator при создании очереди!
 */
public class PriorityQueueEx {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(22);
        priorityQueue.add(4);
        priorityQueue.add(8);
        priorityQueue.add(15);

        // В начале элементы priorityQueue не отсортированы по приоритету
        System.out.println("PriorityQueue = " + priorityQueue);

        // peek() выдаст элемент с наивысшим приоритетом, у Integer это естественный порядок
        System.out.println("The element with highest priority in priorityQueue = " + priorityQueue.peek());

        // remove() удалит элемент с наивысшим приоритетом, у Integer это естественный порядок
        System.out.println("Remove element with highest priority in priorityQueue = " + priorityQueue.remove());
        System.out.println("priorityQueue after removing = " + priorityQueue);

        PriorityQueue<Student> students = new PriorityQueue<>();
        students.add(new Student("Ivan", 5));
        students.add(new Student("Maria", 3));
        students.add(new Student("Iosif", 1));
        students.add(new Student("Alex", 2));
        System.out.println("Students = " + students);

        System.out.println("Delete student with highest priority = " + students.poll());
        System.out.println("Students = " + students);

    }

    private static class Student implements Comparable<Student> {
        private String name;
        private int course;

        public Student(String name, int course) {
            this.name = name;
            this.course = course;
        }

        @Override
        public int compareTo(Student student) {
            return course - student.course;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", course=" + course +
                    '}';
        }
    }
}
