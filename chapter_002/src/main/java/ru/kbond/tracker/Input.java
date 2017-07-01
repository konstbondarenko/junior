package ru.kbond.tracker;

/**
 * Interface Input - служит для создания разных способов ввода данных(ручной, заданный заранее.
 * @author kbondarenko
 * @since 05.06.2017
 * @version 1
 */
public interface Input {
    /**
     * Метод реализующий ввод в меню.
     * @param question - принимаемая строка.
     * @return - return.
     */
    String ask(String question);
    /**
     * Метод реализующий ввод в меню с проверкой данных.
     * @param question - принимаемая строка.
     * @param range - размер массива меню.
     * @return - return.
     */
    int ask(String question, int[] range);
}
