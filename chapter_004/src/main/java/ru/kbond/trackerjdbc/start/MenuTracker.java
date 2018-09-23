package ru.kbond.trackerjdbc.start;

import ru.kbond.trackerjdbc.models.Item;
import java.util.Date;

/**
 * Класс-событие для поиска объекта в списке по его имени.
 */
class FindByName extends BaseAction {
    /**
     * Конструктор.
     *
     * @param name - название пункта меню.
     * @param key  - номер позиции меню.
     */
    FindByName(String name, int key) {
        super(name, key);
    }

    /**
     * Метод реализующий взаимодействие с базой данных.
     *
     * @param input   - способ ввода.
     * @param tracker - база данных с которой производятся действия.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println(tracker.findByName(input.ask("Enter the name : ")));
    }
}

/**
 * Class MenuTracker - меню.
 *
 * @author kbondarenko
 * @version 1
 * @since 22.06.2017
 */
public class MenuTracker {
    /**
     * Поле для взаимодействия со способом ввода данных.
     *
     * @param input - способ ввода.
     */
    private final Input input;
    /**
     * Поле для взаимодействия с базой данных.
     *
     * @param track - база данных заявок.
     */
    private final Tracker tracker;
    /**
     * Поле создающее дату.
     *
     * @param currentDate - дата на момент создания заявки.
     */
    private final Date currentDate = new Date();
    /**
     * Поле создающее дату для заявки.
     *
     * @param time - дата на момент создания заявки переведённая в тип long.
     */
    private final long time = currentDate.getTime();
    /**
     * Поле хранящее события.
     *
     * @param actions - массив событий.
     */
    private UserAction[] actions = new UserAction[8];

    /**
     * Getter.
     *
     * @return - размер массива меню.
     */
    public int getActionsLength() {
        return actions.length;
    }

    /**
     * Поле хранящее номер-позицию для добавления нового элемента в массив-меню.
     *
     * @param position - позиция.
     */
    private int position = 0;

    /**
     * Конструктор.
     *
     * @param input   - принимаемый способ ввода данных.
     * @param tracker - принимаемый массив.
     */
    public MenuTracker(final Input input, final Tracker tracker) {
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
        this.actions[position++] = new FindById("Find the items by id.", 4);
        this.actions[position++] = new FindByName("Find the items by name.", 5);
        this.actions[position++] = new AddItemComment("Add the comment for item.", 6);
        this.actions[position++] = new GetItemComment("Get the comment for item.", 7);
    }

    /**
     * Метод производящий выбор события.
     *
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
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        AddItem(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            Item item = new Item(
                    input.ask("Enter name : "),
                    input.ask("Enter description : "),
                    time);
            System.out.println();
            tracker.add(item);
        }
    }

    /**
     * Класс-событие показывающее все объекты в списке.
     */
    private class ShowAllItems extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(tracker.findAll());
        }
    }

    /**
     * Класс-событие для замены объекта в списке по его id.
     */
    private class EditItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        EditItem(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            int id = Integer.parseInt(input.ask("Enter id : "));
            Item item = new Item(
                    input.ask("Enter name : "),
                    input.ask("Enter description : "),
                    System.currentTimeMillis());
            System.out.println();
            tracker.update(id, item);
        }
    }

    /**
     * Класс-событие для удаления объекта по его id.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            int del = Integer.parseInt(input.ask("Enter id : "));
            tracker.delete(del);
        }
    }

    /**
     * Класс-событие для поиска объекта в списке по его id.
     */
    private static class FindById extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        FindById(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            int find = Integer.parseInt(input.ask("Enter id : "));
            System.out.println(tracker.findById(find));
        }
    }

    /**
     * Класс-событие для добавления комментария к Item.
     */
    private class AddItemComment extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        public AddItemComment(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String comment = input.ask("Enter comment : ");
            int id = Integer.parseInt(input.ask("Enter id : "));
            tracker.addComment(comment, id);
        }
    }

    /**
     * Класс-событие для поиска комментариев к Item.
     */
    private class GetItemComment extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name - название пункта меню.
         * @param key  - номер позиции меню.
         */
        public GetItemComment(String name, int key) {
            super(name, key);
        }

        /**
         * Метод реализующий взаимодействие с базой данных.
         *
         * @param input   - способ ввода.
         * @param tracker - база данных с которой производятся действия.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            int id = Integer.parseInt(input.ask("Enter id : "));
            System.out.println(tracker.getComments(id));
        }
    }
}
