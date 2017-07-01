package ru.kbond.tracker;

/**
 * Class StartUi - точка входа в программу, меню.
 * @author kbondarenko
 * @since 29.05.2017
 * @version 1
 */
public class StartUi {
    /**
     * Поле для взаимодействия со способом ввода данных.
     * @param input - способ ввода.
     */
    private Input input;
    /**
     * Поле для взаимодействия с массивом.
     * @param track - массив заявок.
     */
    private Tracker tracker;
    /**
     * Поле хранящее размер массива меню.
     * @param ranges - размер массива меню.
     */
    private int[] ranges;
    /**
     * Конструктор.
     * @param input - принимаемый способ ввода данных.
     * @param tracker - принимаемый массив.
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Метод реализующий меню.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        this.ranges = new int[menu.getActionsLength()];
        for (int index = 0; index != this.ranges.length; index++) {
            this.ranges[index] = index;
        }
        do {
            menu.show();
            menu.select(input.ask("Select: ", this.ranges));
        } while (!"y".equals(this.input.ask("Exit?(y):")));
    }
    /**
     * Точка входа в программу.
     * @param args - принимаемый аргумент.
     */
    public static void main(String[] args) {
        new StartUi(new ValidateInput(), new Tracker()).init();
    }
}
