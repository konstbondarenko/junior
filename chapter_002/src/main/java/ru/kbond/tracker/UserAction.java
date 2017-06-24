package ru.kbond.tracker;

/**
 * Interface UserAction - используется для описания методов общих для всех событий.
 * @author kbondarenko
 * @since 22.06.2017
 * @version 1
 */
public interface UserAction {
    /**
     * Поле задающее позицию меню.
     * @return - return.
     */
    int key();
    /**
     * Метод реализующий взаимодействие с массивом.
     * @param input - способ ввода.
     * @param tracker - массив с которым производятся действия.
     */
    void execute(Input input, Tracker tracker);
    /**
     * Поле с описанием пункта меню.
     * @return - return.
     */
    String info();
}
