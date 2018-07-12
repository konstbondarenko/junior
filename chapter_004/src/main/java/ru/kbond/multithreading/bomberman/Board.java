package ru.kbond.multithreading.bomberman;

import java.util.concurrent.TimeUnit;
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
     * The method checks if the cell is available for the move,
     * if so then the object grabs the lock and frees the occupied cell.
     *
     * @param source cell occupied by an object.
     * @param dest   the cell into which to go.
     * @return {@code true} if the capture was successful.
     */
    public boolean move(ReentrantLock source, ReentrantLock dest) throws InterruptedException {
        boolean lock = dest.tryLock(500L, TimeUnit.MILLISECONDS);
        if (lock) {
            source.unlock();
        }
        return lock;
    }

    /**
     * Getter.
     */
    public ReentrantLock[][] getBoardCell() {
        return board;
    }
}
