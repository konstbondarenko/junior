package ru.kbond.tracker;

/**
 * Class BaseAction - используется для описания методов общих для всех событий.
 * @author kbondarenko
 * @since 21.07.2017
 * @version 1
 */
public abstract class BaseAction implements UserAction {
    /**
     * Поле хранящее название пункта меню.
     * @param name - название пункта меню.
     */
    private String name;
    /**
     * Поле хранящее номер позиции меню.
     * @param key - номер позиции меню.
     */
    private int key;
    /**
     * Конструктор.
     * @param name - название пункта меню.
     * @param key - номер позиции меню.
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }
    /**
     * Метод задающий позицию меню.
     * @return - пункт меню.
     */
    @Override
    public int key() {
        return this.key;
    }
    /**
     * Поле с описанием пункта меню.
     * @return - строка с номером и названием пункта меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), this.name);
    }
}
