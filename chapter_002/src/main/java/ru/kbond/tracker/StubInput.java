package ru.kbond.tracker;

/**
 * Class StubInput - используется для эмуляции ввода пользовательских данных из консоли.
 * @author kbondarenko
 * @since 05.06.2017
 * @version 1
 */
public class StubInput implements Input {
    /**
     * Поле хранящее массив для ввода в меню.
     * @param answers - заготовленные ответы.
     */
    private String[] answers;
    /**
     * Поле хранящее номер-позицию для ввода в меню.
     * @param position - позиция.
     */
    private int position = 0;
    /**
     * Конструктор.
     * @param answers - принимаемый массив.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }
    /**
     * Метод эмулирующий ввод пользователя.
     * @param question - принимаемая строка.
     * @return результат.
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }
    /**
     * Метод эмулирующий ввод пользователя.
     * @param question - принимаемая строка.
     * @param range - размер массива меню.
     * @return результат.
     */
    @Override
    public int ask(String question, int[] range) {
        return Integer.parseInt(answers[position++]);
    }
}
