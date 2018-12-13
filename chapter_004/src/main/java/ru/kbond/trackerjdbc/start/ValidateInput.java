package ru.kbond.trackerjdbc.start;

import ru.kbond.trackerjdbc.exception.MenuOutException;

/**
 * Class ValidateInput - ввод пользовательских данных из консоли с проверкой на исключения.
 * @author kbondarenko
 * @since 01.07.2017
 * @version 1
 */
public class ValidateInput implements Input {
    /**
     * Поле для взаимодействия со способом ввода данных.
     * @param input - способ ввода.
     */
    private final Input input;
    /**
     * Конструктор.
     * @param input - принимаемый способ ввода данных.
     */
    public ValidateInput(final Input input) {
        this.input = input;
    }
    /**
     * Метод проверяющий нужно ли выйти из меню.
     * @param question - принимаемая строка.
     * @return результат.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }
    /**
     * Метод принимающий ввод пользователя с проверкой на исключения.
     * @param question - принимаемая строка.
     * @param range - размер массива меню.
     * @return результат.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
