package ru.venidiktov.lambda;

/**
 * Функциональный интерфейс
 * Функциональный интерфейс - это интерфейс который содержит только 1 абстрактный метод! дефолтных или статических методов может быть сколько угодно!!!
 */
@FunctionalInterface
public interface MyChecks {
    boolean check(Student student);
}
