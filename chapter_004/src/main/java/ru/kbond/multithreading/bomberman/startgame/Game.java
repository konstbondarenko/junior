package ru.kbond.multithreading.bomberman.startgame;

import ru.kbond.multithreading.bomberman.Board;
import ru.kbond.multithreading.bomberman.Input;

import java.util.concurrent.CountDownLatch;

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
    private CountDownLatch cdl;

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
    public void bomberInit(final String name, final int numberOfMonster) throws InterruptedException {
        setBlocks();
        this.cdl.await();
        setMonster(numberOfMonster);
        this.cdl.await();
        final Bomberman bomberman = new Bomberman(name, board, input);
        Thread bomberRun = new Thread(bomberman);
        System.out.println("старт");
        bomberRun.start();
    }

    /**
     * The method randomly establishes obstacles on the field.
     */
    private void setBlocks() {
        final int numberOfBlocks = 1 + (int) (Math.random() * (this.board.getBoardCell().length));
        this.cdl = new CountDownLatch(numberOfBlocks);
        for (int i = 0; i < numberOfBlocks;) {
            int x = (int) (Math.random() * (this.board.getBoardCell().length - 1));
            int y = (int) (Math.random() * (this.board.getBoardCell().length - 1));
            if (this.board.getBoardCell()[x][y].tryLock()) {
                i++;
                this.cdl.countDown();
            }
        }
    }

    /**
     * The method randomly arranges the monsters on the field.
     *
     * @param numberOfMonster number of monsters.
     */
    private void setMonster(int numberOfMonster) {
        StringBuilder stringBuilder = new StringBuilder();
        this.cdl = new CountDownLatch(numberOfMonster);
        for (int i = 0; i < numberOfMonster; i++) {
            stringBuilder.append("Monster-").append(i);
            final Monster monster = new Monster(stringBuilder.toString(), this.board);
            Thread thread = new Thread(monster);
            thread.setName(stringBuilder.toString());
            thread.setDaemon(true);
            thread.start();
            stringBuilder = new StringBuilder();
            this.cdl.countDown();
        }
    }
}
