package ru.kbond.multithreading.bomberman.startgame;

import ru.kbond.multithreading.bomberman.*;

import java.util.concurrent.TimeUnit;

/**
 * The class describes the character's behavior as a Bomberman.
 *
 * @author kbondarenko
 * @version 1
 * @since 09.07.2018
 */
public class Bomberman implements Personage, Runnable {
    private final BasePersonage basePersonage;
    private final Input input;

    /**
     * Constructor.
     *
     * @param name  character `s name.
     * @param board field where the game takes place.
     * @param input the way the character is controlled.
     */
    public Bomberman(final String name, final Board board, final Input input) {
        this.input = input;
        this.basePersonage = new BasePersonage(name, board);
    }

    /**
     * The method pre-sets the character on the playing field.
     */
    @Override
    public void run() {
        this.basePersonage.setCharacter();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                validateMove(this.input.ask("row"), this.input.ask("column"));
            } catch (ArrayIndexOutOfBoundsException ar) {
                System.out.println("Выход за пределы поля, повторите ход.");
            } catch (InvalidStepException inv) {
                System.out.println(inv.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The method moves the player according to the specified coordinates.
     * Produces a check for the admissibility of the move, checks whether
     * the monster would like to get access to the player's cage.
     *
     * @param xNew the coordinate of the row of the playing field.
     * @param yNew the coordinate of the column of the playing field.
     * @throws InterruptedException it is thrown out in case of a meeting
     *                              of the player with the monster.
     */
    private void validateMove(int xNew, int yNew) throws InterruptedException {
        int xTmp = this.basePersonage.getX();
        int yTmp = this.basePersonage.getY();
        if (this.basePersonage.getBoard().getBoardCell()[xTmp][yTmp].hasQueuedThreads()) {
            Thread.currentThread().interrupt();
        }
        if (Math.abs(xTmp - xNew) > 1 || Math.abs(yTmp - yNew) > 1) {
            throw new InvalidStepException("Ход не может превышать 1 клетку, повторите ход.");
        }
        if (!this.basePersonage.getBoard().move(
                this.basePersonage.getBoard().getBoardCell()[xTmp][yTmp],
                this.basePersonage.getBoard().getBoardCell()[xNew][yNew])
                ) {
            throw new InvalidStepException("Клетка занята, повторите ход.");
        }
        this.basePersonage.setX(xNew);
        this.basePersonage.setY(yNew);
        TimeUnit.SECONDS.sleep(1);
    }
}
