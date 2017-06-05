package ru.kbond.tracker;

import java.util.Scanner;

/**
 * Class ConsoleInput - используется для ввода пользовательских данных из консоли.
 * @author kbondarenko
 * @since 29.05.2017
 * @version 1
 */
public class ConsoleInput implements Input {
    /**
     * Поле scanner.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Метод принимающий ввод пользователя.
     * @param question - принимаемая строка.
     * @return результат.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
