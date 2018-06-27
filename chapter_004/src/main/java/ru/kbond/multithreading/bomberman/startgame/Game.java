package ru.kbond.multithreading.bomberman.startgame;

import ru.kbond.multithreading.bomberman.BasePersonage;
import ru.kbond.multithreading.bomberman.Board;

/**
 * The class initializes the players.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.06.2018
 */
public class Game {
    private final Board board;

    /**
     * Constructor.
     */
    public Game(Board board) {
        this.board = board;
    }

    /**
     * Sets the player on the field and his name.
     *
     * @param name player name.
     */
    public void bomberInit(String name) {
        final BasePersonage bomber = new BasePersonage(name, this.board);
        Thread bomberRun = new Thread(bomber);
        bomberRun.start();
    }
}
