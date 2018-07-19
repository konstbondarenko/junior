package ru.kbond.multithreading.bomberman.startgame;

import ru.kbond.multithreading.bomberman.BasePersonage;
import ru.kbond.multithreading.bomberman.Board;
import ru.kbond.multithreading.bomberman.Personage;
import java.util.concurrent.TimeUnit;

/**
 * The class describes the character's behavior as a Bomberman.
 *
 * @author kbondarenko
 * @version 1
 * @since 19.07.2018
 */
public class Monster implements Personage, Runnable {
    private final BasePersonage basePersonage;

    /**
     * Constructor.
     */
    public Monster(final String name, final Board board) {
        this.basePersonage = new BasePersonage(name, board);
    }

    /**
     * The method performs installation of the monster on
     * the field and automatic movement.
     */
    @Override
    public void run() {
        this.basePersonage.setCharacter();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.SECONDS.sleep(5);
                this.basePersonage.automaticMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
