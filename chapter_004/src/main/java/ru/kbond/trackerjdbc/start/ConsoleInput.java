package ru.kbond.trackerjdbc.start;

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
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
    /**
     * Метод принимающий ввод пользователя и выбрасывающий исключение
     * в случае выхода за пределы массива.
     * @param question - принимаемая строка.
     * @param range - размер массива меню.
     * @return результат.
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }

}
