package ru.kbond.tracker;

/**
 * Class ValidateInput - ввод пользовательских данных из консоли с проверкой на исключения.
 * @author kbondarenko
 * @since 01.07.2017
 * @version 1
 */
public class ValidateInput extends ConsoleInput {
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
                value = super.ask(question, range);
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
