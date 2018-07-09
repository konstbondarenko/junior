package ru.kbond.multithreading.bomberman.startgame;

import ru.kbond.multithreading.bomberman.Board;
import ru.kbond.multithreading.bomberman.Input;

/**
 * The class initializes the players.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.06.2018
 */
public class Game {
    private final Board board;
    private final Input input;

    /**
     * Constructor.
     */
    public Game(final Board board, final Input input) {
        this.board = board;
        this.input = input;
    }

    /**
     * Sets the player on the field and his name.
     *
     * @param name player name.
     */
    public void bomberInit(final String name) {
        final Bomberman bomberman = new Bomberman(name, board, input);
        Thread bomberRun = new Thread(bomberman);
        System.out.println("старт");
        bomberRun.start();
    }
}
