package ru.venidiktov.collection.list.stack;

import java.util.Stack;

/***
 * Stack устаревший synchronized класс использует принцип LIFO, не рекомендуется использовать
 */
public class StackEx {
    public static void main(String[] args) {
//        System.out.println("Start main");
//        abc3();
//        System.out.println("End main");

        Stack<String> stack = new Stack<>();
        stack.push("Maria");
        stack.push("Iosif");
        stack.push("Ivan");
        System.out.println("Stack = " + stack);

        //pop - Достает элемент сверху и удаляет его
        System.out.println("pop() element from tail stack = " + stack.pop());
        System.out.println("Stack after pop() method = " + stack);

        //isEmpty
        System.out.println("Stack is empty = " + stack.isEmpty());

        //peek - Достает элемент сверху и не удаляет его
        System.out.println("peek(), top element stack = " + stack.peek());
        System.out.println("Stack after peek() method = " + stack);
    }

    static void abc1() {
        System.out.println("abc1 starts");
        System.out.println("abc1 ends");
    }

    static void abc2() {
        System.out.println("abc2 starts");
        abc1();
        System.out.println("abc2 ends");
    }

    static void abc3() {
        System.out.println("abc3 starts");
        abc2();
        System.out.println("abc3 ends");
    }
}
