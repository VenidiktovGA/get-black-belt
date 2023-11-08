package ru.venidiktov.regex;

/**
 * Для форматированного вывода текста можно использовать методы:
 * printf() у объекта System.out класс java.io.PrintStream
 * <p>
 * В классе String есть метод format который так же использует спецификаторы для форматирования строки
 * <p>
 * Спецификатор определяющий формат данных выглядит так %[flags][width][.precision]datatype_specifier
 *
 * @link https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
 */
public class PrintfAndFormatEx {
    public static void main(String[] args) {

        /**
         * Метод printf использует спецификаторы для указания формата и тапа вставляемых данных в исходную строку,
         * например %s - означает обычную строку
         */
        System.out.printf("C помощью метода printf() класса %s можно выводить красиво", System.out.getClass().getName());
        System.out.println();

        /**
         * Выравнивание и дополнение нулями работает когда мы задаем ширину
         */
        System.out.println(String.format("%d", 101));
        System.out.println(String.format("|%10d|", 101));  // Specifying length of integer
        System.out.println(String.format("|%-10d|", 101)); // Left-justifying within the specified width
        System.out.println(String.format("|% d|", 101));
        System.out.println(String.format("|%010d|", 101)); // Filling with zeroes
        System.out.println();

        var emp1 = new Employee(1, "Iosif", "Markov", 12345, 0.15);
        var emp2 = new Employee(15, "Maria", "Ivanova", 5443, 0.45);
        var emp3 = new Employee(333, "Alex", "Lion", 12345, 0.15);
        employeeInfo(emp1);
        employeeInfo(emp2);
        employeeInfo(emp3);
    }

    private static void employeeInfo(Employee employee) {
        var str = String.format("%03d \t %-12s \t %-12s \t %,.1f",
                employee.id, employee.name, employee.surname, employee.salary * (1 + employee.bonusPct));
        System.out.println(str);
    }

    private static class Employee {
        int id;
        String name;
        String surname;
        int salary;
        double bonusPct;

        public Employee(int id, String name, String surname, int salary, double bonusPct) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.salary = salary;
            this.bonusPct = bonusPct;
        }
    }
}
