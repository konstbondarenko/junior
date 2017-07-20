package ru.kbond.tracker;

import java.util.Arrays;
import java.util.Date;

/**
 * Класс-событие для поиска объекта в списке по его имени.
 */
class FindByName extends BaseAction {
    /**
     * Конструктор.
     * @param name - название пункта меню.
     * @param key - номер позиции меню.
     */
    FindByName(String name, int key) {
        super(name, key);
    }
    /**
     * Метод реализующий взаимодействие с массивом.
     * @param input - способ ввода.
     * @param tracker - массив с которым производятся действия.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println(Arrays.toString(tracker.findByName(input.ask("Enter the name : "))));
    }
}
/**
 * Class MenuTracker - меню.
 * @author kbondarenko
 * @since 22.06.2017
 * @version 1
 */
public class MenuTracker {
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
     * Поле создающее дату.
     * @param currentDate - дата на момент создания заявки.
     */
    private Date currentDate = new Date();
    /**
     * Поле создающее дату для заявки.
     * @param time - дата на момент создания заявки переведённая в тип long.
     */
    private long time = currentDate.getTime();
    /**
     * Поле хранящее события.
     * @param actions - массив событий.
     */
    private UserAction[] actions = new UserAction[6];
    /**
     * Getter.
     * @return - размер массива меню.
     */
    public int getActionsLength() {
        return actions.length;
    }
    /**
     * Поле хранящее номер-позицию для добавления нового элемента в массив-меню.
     * @param position - позиция.
     */
    private int position = 0;
    /**
     * Конструктор.
     * @param input - принимаемый способ ввода данных.
     * @param tracker - принимаемый массив.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Метод добавляющий события в массив.
     */
    public void fillActions() {
        this.actions[position++] = this.new AddItem("Add the new item.", 0);
        this.actions[position++] = this.new ShowAllItems("Show all items.", 1);
        this.actions[position++] = this.new EditItem("Edit the items.", 2);
        this.actions[position++] = this.new DeleteItem("Delete the items.", 3);
        this.actions[position++] = new MenuTracker.FindById("Find the items by id.", 4);
        this.actions[position++] = new FindByName("Find the items by name.", 5);
    }
    /**
     * Метод производящий выбор события.
     * @param key - номер пункта меню.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }
    /**
     * Метод показывающий описание события.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
    /**
     * Класс-событие добавляющее новый объект.
     */
    private class AddItem extends BaseAction {
        /**
         * Конструктор.
         * @param name - название пункта меню.
         * @param key - номер позиции меню.
         */
        AddItem(String name, int key) {
            super(name, key);
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            Item item = new Item(input.ask("Enter id : "),
                    input.ask("Enter name : "),
                    input.ask("Enter description : "),
                    time,
                    new String[] {input.ask("Enter comments : ")});
            System.out.println("");
            tracker.add(item);
        }
    }
    /**
     * Класс-событие показывающее все объекты в списке.
     */
    private class ShowAllItems extends BaseAction {
        /**
         * Конструктор.
         * @param name - название пункта меню.
         * @param key - номер позиции меню.
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(Arrays.toString(tracker.findAll()));
        }
    }
    /**
     * Класс-событие для замены объекта в списке по его id.
     */
    private class EditItem extends BaseAction {
        /**
         * Конструктор.
         * @param name - название пункта меню.
         * @param key - номер позиции меню.
         */
        EditItem(String name, int key) {
            super(name, key);
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            Item item = new Item(input.ask("Enter id : "),
                    input.ask("Enter name : "),
                    input.ask("Enter description : "),
                    time,
                    new String[] {input.ask("Enter comments : ")});
            System.out.println("");
            tracker.update(item);
        }
    }
    /**
     * Класс-событие для удаления объекта по его id.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         * @param name - название пункта меню.
         * @param key - номер позиции меню.
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.delete(input.ask("Enter id : "));
        }
    }
    /**
     * Класс-событие для поиска объекта в списке по его id.
     */
    private static class FindById extends BaseAction {
        /**
         * Конструктор.
         * @param name - название пункта меню.
         * @param key - номер позиции меню.
         */
        FindById(String name, int key) {
            super(name, key);
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(Arrays.toString(tracker.findById(input.ask("Enter id : "))));
        }
    }
}
