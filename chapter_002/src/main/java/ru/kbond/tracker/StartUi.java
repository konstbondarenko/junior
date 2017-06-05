package ru.kbond.tracker;

import java.util.Arrays;
import java.util.Date;

/**
 * Class StartUi - точка входа в программу, меню.
 * @author kbondarenko
 * @since 29.05.2017
 * @version 1
 */
public class StartUi {
    /**
     * Поле хранящее ключ для меню.
     * @param ADD - ключ для метода добавления.
     */
    private static final int ADD = 0;
    /**
     * Поле хранящее ключ для меню.
     * @param SHOW_AL - ключ для метода показа всего списка.
     */
    private static final int SHOW_ALL = 1;
    /**
     * Поле хранящее ключ для меню.
     * @param EDIT - ключ для метода замены.
     */
    private static final int EDIT = 2;
    /**
     * Поле хранящее ключ для меню.
     * @param DELETE - ключ для метода удаления.
     */
    private static final int DELETE = 3;
    /**
     * Поле хранящее ключ для меню.
     * @param FIND_BY_ID - ключ для метода поиска по id.
     */
    private static final int FIND_BY_ID = 4;
    /**
     * Поле хранящее ключ для меню.
     * @param FIND_BY_NAME - ключ для метода поиска по имени.
     */
    private static final int FIND_BY_NAME = 5;
    /**
     * Поле хранящее ключ для меню.
     * @param EXIT - ключ для выхода из приложения.
     */
    private static final int EXIT = 6;
    /**
     * Поле для взаимодействия со способом ввода данных.
     * @param input - массив заявок.
     */
    private Input input;
    /**
     * Поле для взаимодействия с массивом.
     * @param track - массив заявок.
     */
    private Tracker track;
    /**
     * Конструктор.
     * @param input - принимаемый способ ввода данных.
     * @param track - принимаемый массив.
     */
    public StartUi(Input input, Tracker track) {
        this.input = input;
        this.track = track;
    }
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
     * Метод реализующий меню.
     */
    public void init() {
        String askForUser;
        int choiceMenu;
        do {
            System.out.println("0. Add new Item");
            System.out.println("1. Show all items");
            System.out.println("2. Edit item");
            System.out.println("3. Delete item");
            System.out.println("4. Find item by Id");
            System.out.println("5. Find items by name");
            System.out.println("6. Exit Program");
            askForUser = this.input.ask("Select menu number: ");
            choiceMenu = Integer.valueOf(askForUser);
            switch (choiceMenu) {
                case ADD:
                    addItem();
                    break;
                case SHOW_ALL:
                    showAll();
                    break;
                case EDIT:
                    editItem();
                    break;
                case DELETE:
                    deleteItem();
                    break;
                case FIND_BY_ID:
                    findById();
                    break;
                case FIND_BY_NAME:
                    findByName();
                    break;
                default:
            }
        } while (choiceMenu != EXIT);
    }
    /**
     * Метод добавляющий объект в массив.
     */
    public void addItem() {
        Item item = new Item(this.input.ask("Enter id : "),
                this.input.ask("Enter name : "),
                this.input.ask("Enter description : "),
                time,
                new String[] {this.input.ask("Enter comments : ")});
        this.track.add(item);
        System.out.println("");
    }
    /**
     * Метод показывающий список всех объектов.
     */
    public void showAll() {
        System.out.println(Arrays.toString(this.track.findAll()));
        System.out.println("");
    }
    /**
     * Метод заменяющий объект в массиве.
     */
    public void editItem() {
        Item itemUpdate = new Item(this.input.ask("Enter id : "),
                this.input.ask("Enter name : "),
                this.input.ask("Enter description : "),
                time,
                new String[] {this.input.ask("Enter comments : ")});
        this.track.update(itemUpdate);
        System.out.println("");
    }
    /**
     * Метод удаляющий объект из массива.
     */
    public void deleteItem() {
        this.track.delete(this.input.ask("Enter id : "));
        System.out.println("");
    }
    /**
     * Метод производящий поиск объекта по id.
     */
    public void findById() {
        System.out.println(this.track.findById(this.input.ask("Enter id : ")));
        System.out.println("");
    }
    /**
     * Метод производящий поиск объекта по имени.
     */
    public void findByName() {
        System.out.println(Arrays.toString(this.track.findByName(this.input.ask("Enter name : "))));
        System.out.println("");
    }
    /**
     * Точка входа в программу.
     * @param args - принимаемый аргумент.
     */
    public static void main(String[] args) {
        StartUi ui = new StartUi(new ConsoleInput(), new Tracker());
        ui.init();
    }
}
