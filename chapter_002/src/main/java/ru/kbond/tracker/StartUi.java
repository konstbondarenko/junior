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
     * Метод реализующий меню.
     */
    public void init() {
        ConsoleInput consoleInput = new ConsoleInput();
        Tracker track = new Tracker();
        Date currentDate = new Date();
        long time = currentDate.getTime();
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
            askForUser = consoleInput.ask("Select menu number: ");
            choiceMenu = Integer.valueOf(askForUser);
            switch (choiceMenu) {
                case 0:
                    Item item1 = new Item(consoleInput.ask("Enter id : "),
                            consoleInput.ask("Enter name : "),
                            consoleInput.ask("Enter description : "),
                            time,
                            new String[] {consoleInput.ask("Enter comments : ")});
                    track.add(item1);
                    System.out.println("");
                    break;
                case 1:
                    System.out.println(Arrays.toString(track.findAll()));
                    System.out.println("");
                    break;
                case 2:
                    Item item2 = new Item(consoleInput.ask("Enter id : "),
                            consoleInput.ask("Enter name : "),
                            consoleInput.ask("Enter description : "),
                            time,
                            new String[] {consoleInput.ask("Enter comments : ")});
                    track.update(item2);
                    System.out.println("");
                    break;
                case 3:
                    track.delete(consoleInput.ask("Enter id : "));
                    System.out.println("");
                    break;
                case 4:
                    System.out.println(track.findById(consoleInput.ask("Enter id : ")));
                    System.out.println("");
                    break;
                case 5:
                    System.out.println(Arrays.toString(track.findByName(consoleInput.ask("Enter name : "))));
                    System.out.println("");
                    break;
                default:
            }
        } while (choiceMenu != 6);
    }
    /**
     * Точка входа в программу.
     * @param args - принимаемый аргумент.
     */
    public static void main(String[] args) {
        StartUi ui = new StartUi();
        ui.init();
    }
}
