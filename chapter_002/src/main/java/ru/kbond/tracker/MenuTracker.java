package ru.kbond.tracker;

import java.util.Arrays;
import java.util.Date;

/**
 * Класс-событие для поиска объекта в списке по его имени.
 */
class FindByName implements UserAction {
    /**
     * Метод c номером пункта меню.
     * @return - пункт меню.
     */
    @Override
    public int key() {
        return 5;
    }
    /**
     * Метод реализующий взаимодействие с массивом.
     * @param input - способ ввода.
     * @param tracker - массив с которым производятся действия.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        tracker.findByName(input.ask("Enter the name : "));
    }
    /**
     * Поле с описанием пункта меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find the items by name.");
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
        this.actions[0] = this.new AddItem();
        this.actions[1] = this.new ShowAllItems();
        this.actions[2] = this.new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = new MenuTracker.FindById();
        this.actions[5] = new FindByName();
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
    private class AddItem implements UserAction {
        /**
         * Метод c номером пункта меню.
         * @return - пункт меню.
         */
        @Override
        public int key() {
            return 0;
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
        /**
         * Поле с описанием пункта меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }
    /**
     * Класс-событие показывающее все объекты в списке.
     */
    private class ShowAllItems implements UserAction {
        /**
         * Метод c номером пункта меню.
         * @return - пункт меню.
         */
        @Override
        public int key() {
            return 1;
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
        /**
         * Поле с описанием пункта меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }
    /**
     * Класс-событие для замены объекта в списке по его id.
     */
    private class EditItem implements UserAction {
        /**
         * Метод c номером пункта меню.
         * @return - пункт меню.
         */
        @Override
        public int key() {
            return 2;
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
        /**
         * Поле с описанием пункта меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit the items.");
        }
    }
    /**
     * Класс-событие для удаления объекта по его id.
     */
    private class DeleteItem implements UserAction {
        /**
         * Метод c номером пункта меню.
         * @return - пункт меню.
         */
        @Override
        public int key() {
            return 3;
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
        /**
         * Поле с описанием пункта меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete the items.");
        }
    }
    /**
     * Класс-событие для поиска объекта в списке по его id.
     */
    private static class FindById implements UserAction {
        /**
         * Метод c номером пункта меню.
         * @return - пункт меню.
         */
        @Override
        public int key() {
            return 4;
        }
        /**
         * Метод реализующий взаимодействие с массивом.
         * @param input - способ ввода.
         * @param tracker - массив с которым производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.findById(input.ask("Enter id : "));
        }
        /**
         * Поле с описанием пункта меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find the items by id.");
        }
    }
}
