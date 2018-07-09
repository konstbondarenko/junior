package ru.kbond.multithreading.bomberman;

import java.util.Scanner;

/**
 * The class is used to enter data from the console.
 *
 * @author kbondarenko
 * @version 1
 * @since 09.07.2018
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * The method that implements the input of coordinates.
     *
     * @param question received string.
     * @return player's coordinate.
     */
    public int ask(String question) {
        System.out.println(question);
        return scanner.nextInt();
    }
}
