package ru.kbond.multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is a two-dimensional array
 * emulating the game field.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.06.2018
 */
public class Board {
    private final ReentrantLock[][] board;

    /**
     * Constructor.
     * Initializes a random-size field
     * of limited size 10.
     */
    public Board() {
        int xY = 5 + (int) (Math.random() * ((10 - 5) + 1));
        this.board = new ReentrantLock[xY][xY];
        for (int i = 0; i < xY; i++) {
            for (int j = 0; j < xY; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Getter.
     */
    public ReentrantLock[][] getBoardCell() {
        return board;
    }
}
