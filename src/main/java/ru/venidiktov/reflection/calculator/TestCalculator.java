package ru.venidiktov.reflection.calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Пример функциональности которую сложно или невозможно сделать без reflection
 * <p>
 * Многое можно улучшить в этом примере, но как простой пример подойдет
 */
public class TestCalculator {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ru/venidiktov/reflection/calculator/calculator.txt"))) {
            String methodName = reader.readLine();
            String firstArgument = reader.readLine();
            String secondArgument = reader.readLine();

            Calculator calculator = new Calculator();
            Class calculatorClass = calculator.getClass();
            Method method = null;
            Method[] calculatorClassMethods = calculatorClass.getDeclaredMethods();
            for (Method classMethod : calculatorClassMethods) {
                if (classMethod.getName().equals(methodName)) {
                    method = classMethod;
                }
            }

            method.invoke(calculator, Integer.parseInt(firstArgument), Integer.parseInt(secondArgument));

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        ;
    }
}
